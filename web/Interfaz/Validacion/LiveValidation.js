var LiveValidation = function (element, optionsObj) {
    this.initialize(element, optionsObj);
}

LiveValidation.VERSION = '1.3 standalone';

LiveValidation.TEXTAREA = 1;
LiveValidation.TEXT = 2;
LiveValidation.PASSWORD = 3;
LiveValidation.CHECKBOX = 4;
LiveValidation.SELECT = 5;
LiveValidation.FILE = 6;

LiveValidation.massValidate = function (validations) {
    var returnValue = true;
    for (var i = 0, len = validations.length; i < len; ++i) {
        var valid = validations[i].validate();
        if (returnValue)
            returnValue = valid;
    }
    return returnValue;
}

LiveValidation.prototype = {
    validClass: 'LV_valid',
    invalidClass: 'LV_invalid',
    messageClass: 'LV_validation_message',
    validFieldClass: 'LV_valid_field',
    invalidFieldClass: 'LV_invalid_field',
    initialize: function (element, optionsObj) {
        var self = this;
        if (!element)
            throw new Error("LiveValidation::initialize - No element reference or element id has been provided!");
        this.element = element.nodeName ? element : document.getElementById(element);
        if (!this.element)
            throw new Error("LiveValidation::initialize - No element with reference or id of '" + element + "' exists!");
        // default properties that could not be initialised above
        this.validations = [];
        this.elementType = this.getElementType();
        this.form = this.element.form;
        // options
        var options = optionsObj || {};
        // error *
        this.validMessage = options.validMessage || '';
        var node = options.insertAfterWhatNode || this.element;
        this.insertAfterWhatNode = node.nodeType ? node : document.getElementById(node);
        this.onValid = options.onValid || function () {
            this.insertMessage(this.createMessageSpan());
            this.addFieldClass();
        };
        this.onInvalid = options.onInvalid || function () {
            this.insertMessage(this.createMessageSpan());
            this.addFieldClass();
        };
        this.onlyOnBlur = options.onlyOnBlur || false;
        this.wait = options.wait || 0;
        this.onlyOnSubmit = options.onlyOnSubmit || false;
        // add to form if it has been provided
        if (this.form) {
            this.formObj = LiveValidationForm.getInstance(this.form);
            this.formObj.addField(this);
        }
        // events
        // collect old events
        this.oldOnFocus = this.element.onfocus || function () {};
        this.oldOnBlur = this.element.onblur || function () {};
        this.oldOnClick = this.element.onclick || function () {};
        this.oldOnChange = this.element.onchange || function () {};
        this.oldOnKeyup = this.element.onkeyup || function () {};
        this.element.onfocus = function (e) {
            self.doOnFocus(e);
            return self.oldOnFocus.call(this, e);
        }
        if (!this.onlyOnSubmit) {
            switch (this.elementType) {
                case LiveValidation.CHECKBOX:
                    this.element.onclick = function (e) {
                        self.validate();
                        return self.oldOnClick.call(this, e);
                    }
                    // let it run into the next to add a change event too
                case LiveValidation.SELECT:
                case LiveValidation.FILE:
                    this.element.onchange = function (e) {
                        self.validate();
                        return self.oldOnChange.call(this, e);
                    }
                    break;
                default:
                    if (!this.onlyOnBlur)
                        this.element.onkeyup = function (e) {
                            self.deferValidation();
                            return self.oldOnKeyup.call(this, e);
                        }
                    this.element.onblur = function (e) {
                        self.doOnBlur(e);
                        return self.oldOnBlur.call(this, e);
                    }
            }
        }
    },
    destroy: function () {
        if (this.formObj) {
            // remove the field from the LiveValidationForm
            this.formObj.removeField(this);
            // destroy the LiveValidationForm if no LiveValidation fields left in it
            this.formObj.destroy();
        }
        // remove events - set them back to the previous events
        this.element.onfocus = this.oldOnFocus;
        if (!this.onlyOnSubmit) {
            switch (this.elementType) {
                case LiveValidation.CHECKBOX:
                    this.element.onclick = this.oldOnClick;
                    // let it run into the next to add a change event too
                case LiveValidation.SELECT:
                case LiveValidation.FILE:
                    this.element.onchange = this.oldOnChange;
                    break;
                default:
                    if (!this.onlyOnBlur)
                        this.element.onkeyup = this.oldOnKeyup;
                    this.element.onblur = this.oldOnBlur;
            }
        }
        this.validations = [];
        this.removeMessageAndFieldClass();
    },
    add: function (validationFunction, validationParamsObj) {
        this.validations.push({
            type: validationFunction,
            params: validationParamsObj || {}
        });
        return this;
    },
    remove: function (validationFunction, validationParamsObj) {
        var found = false;
        for (var i = 0, len = this.validations.length; i < len; i++) {
            if (this.validations[i].type == validationFunction) {
                if (this.validations[i].params == validationParamsObj) {
                    found = true;
                    break;
                }
            }
        }
        if (found)
            this.validations.splice(i, 1);
        return this;
    },
    deferValidation: function (e) {
        if (this.wait >= 300)
            this.removeMessageAndFieldClass();
        var self = this;
        if (this.timeout)
            clearTimeout(self.timeout);
        this.timeout = setTimeout(function () {
            self.validate()
        }, self.wait);
    },
    doOnBlur: function (e) {
        this.focused = false;
        this.validate(e);
    },
    doOnFocus: function (e) {
        this.focused = true;
        this.removeMessageAndFieldClass();
    },
    getElementType: function () {
        switch (true) {
            case (this.element.nodeName.toUpperCase() == 'TEXTAREA'):
                return LiveValidation.TEXTAREA;
            case (this.element.nodeName.toUpperCase() == 'INPUT' && this.element.type.toUpperCase() == 'TEXT'):
                return LiveValidation.TEXT;
            case (this.element.nodeName.toUpperCase() == 'INPUT' && this.element.type.toUpperCase() == 'PASSWORD'):
                return LiveValidation.PASSWORD;
            case (this.element.nodeName.toUpperCase() == 'INPUT' && this.element.type.toUpperCase() == 'CHECKBOX'):
                return LiveValidation.CHECKBOX;
            case (this.element.nodeName.toUpperCase() == 'INPUT' && this.element.type.toUpperCase() == 'FILE'):
                return LiveValidation.FILE;
            case (this.element.nodeName.toUpperCase() == 'SELECT'):
                return LiveValidation.SELECT;
            case (this.element.nodeName.toUpperCase() == 'INPUT'):
                throw new Error('LiveValidation::getElementType - Cannot use LiveValidation on an ' + this.element.type + ' input!');
            default:
                throw new Error('LiveValidation::getElementType - Element must be an input, select, or textarea!');
        }
    },
    doValidations: function () {
        this.validationFailed = false;
        for (var i = 0, len = this.validations.length; i < len; ++i) {
            var validation = this.validations[i];
            switch (validation.type) {
                case Validate.Presence:
                case Validate.Confirmation:
                case Validate.Acceptance:
                    this.displayMessageWhenEmpty = true;
                    this.validationFailed = !this.validateElement(validation.type, validation.params);
                    break;
                default:
                    this.validationFailed = !this.validateElement(validation.type, validation.params);
                    break;
            }
            if (this.validationFailed)
                return false;
        }
        this.message = this.validMessage;
        return true;
    },
    validateElement: function (validationFunction, validationParamsObj) {
        var value = (this.elementType == LiveValidation.SELECT) ? this.element.options[this.element.selectedIndex].value : this.element.value;
        if (validationFunction == Validate.Acceptance) {
            if (this.elementType != LiveValidation.CHECKBOX)
                throw new Error('LiveValidation::validateElement - Element to validate acceptance must be a checkbox!');
            value = this.element.checked;
        }
        var isValid = true;
        try {
            validationFunction(value, validationParamsObj);
        } catch (error) {
            if (error instanceof Validate.Error) {
                if (value !== '' || (value === '' && this.displayMessageWhenEmpty)) {
                    this.validationFailed = true;
                    this.message = error.message;
                    isValid = false;
                }
            } else {
                throw error;
            }
        } finally {
            return isValid;
        }
    },
    validate: function () {
        if (!this.element.disabled) {
            var isValid = this.doValidations();
            if (isValid) {
                this.onValid();
                return true;
            } else {
                this.onInvalid();
                return false;
            }
        } else {
            return true;
        }
    },
    enable: function () {
        this.element.disabled = false;
        return this;
    },
    disable: function () {
        this.element.disabled = true;
        this.removeMessageAndFieldClass();
        return this;
    },
    createMessageSpan: function () {
        var span = document.createElement('span');
        var textNode = document.createTextNode(this.message);
        span.appendChild(textNode);
        return span;
    },
    insertMessage: function (elementToInsert) {
        this.removeMessage();
        if ((this.displayMessageWhenEmpty && (this.elementType == LiveValidation.CHECKBOX || this.element.value == ''))
                || this.element.value != '') {
            var className = this.validationFailed ? this.invalidClass : this.validClass;
            elementToInsert.className += ' ' + this.messageClass + ' ' + className;
            if (this.insertAfterWhatNode.nextSibling) {
                this.insertAfterWhatNode.parentNode.insertBefore(elementToInsert, this.insertAfterWhatNode.nextSibling);
            } else {
                this.insertAfterWhatNode.parentNode.appendChild(elementToInsert);
            }
        }
    },
    addFieldClass: function () {
        this.removeFieldClass();
        if (!this.validationFailed) {
            if (this.displayMessageWhenEmpty || this.element.value != '') {
                if (this.element.className.indexOf(this.validFieldClass) == -1)
                    this.element.className += ' ' + this.validFieldClass;
            }
        } else {
            if (this.element.className.indexOf(this.invalidFieldClass) == -1)
                this.element.className += ' ' + this.invalidFieldClass;
        }
    },
    removeMessage: function () {
        var nextEl;
        var el = this.insertAfterWhatNode;
        while (el.nextSibling) {
            if (el.nextSibling.nodeType === 1) {
                nextEl = el.nextSibling;
                break;
            }
            el = el.nextSibling;
        }
        if (nextEl && nextEl.className.indexOf(this.messageClass) != -1)
            this.insertAfterWhatNode.parentNode.removeChild(nextEl);
    },
    removeFieldClass: function () {
        if (this.element.className.indexOf(this.invalidFieldClass) != -1)
            this.element.className = this.element.className.split(this.invalidFieldClass).join('');
        if (this.element.className.indexOf(this.validFieldClass) != -1)
            this.element.className = this.element.className.split(this.validFieldClass).join(' ');
    },
    removeMessageAndFieldClass: function () {
        this.removeMessage();
        this.removeFieldClass();
    }

}

var LiveValidationForm = function (element) {
    this.initialize(element);
}

LiveValidationForm.instances = {};

LiveValidationForm.getInstance = function (element) {
    var rand = Math.random() * Math.random();
    if (!element.id)
        element.id = 'formId_' + rand.toString().replace(/\./, '') + new Date().valueOf();
    if (!LiveValidationForm.instances[element.id])
        LiveValidationForm.instances[element.id] = new LiveValidationForm(element);
    return LiveValidationForm.instances[element.id];
}

LiveValidationForm.prototype = {
    initialize: function (element) {
        this.name = element.id;
        this.element = element;
        this.fields = [];
        // preserve the old onsubmit event
        this.oldOnSubmit = this.element.onsubmit || function () {};
        var self = this;
        this.element.onsubmit = function (e) {
            return (LiveValidation.massValidate(self.fields)) ? self.oldOnSubmit.call(this, e || window.event) !== false : false;
        }
    },
    addField: function (newField) {
        this.fields.push(newField);
    },
    removeField: function (victim) {
        var victimless = [];
        for (var i = 0, len = this.fields.length; i < len; i++) {
            if (this.fields[i] !== victim)
                victimless.push(this.fields[i]);
        }
        this.fields = victimless;
    },
    destroy: function (force) {
        // only destroy if has no fields and not being forced
        if (this.fields.length != 0 && !force)
            return false;
        // remove events - set back to previous events
        this.element.onsubmit = this.oldOnSubmit;
        // remove from the instances namespace
        LiveValidationForm.instances[this.name] = null;
        return true;
    }

}

var Validate = {
    Presence: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        //error *
        var message = paramsObj.failureMessage || "";
        if (value === '' || value === null || value === undefined) {
            Validate.fail(message);
        }
        return true;
    },
    PresenceSelect: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        //error *
        var message = paramsObj.failureMessage || "";
        if (value === '' || value === null || value === '0' || value === undefined) {
            Validate.fail(message);
        }
        return true;
    },
    Password: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&#.$($)$-$_])[A-Za-z\d$@$!%*?&#.$($)$-$_]{8,15}$/
        });
        return true;
    },
    Password_1: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /[0-9]{1,}/i
        });
        return true;
    },
    Numericality: function (value, paramsObj) {
        var suppliedValue = value;
        var value = Number(value);
        var paramsObj = paramsObj || {};
        var minimum = ((paramsObj.minimum) || (paramsObj.minimum == 0)) ? paramsObj.minimum : null;
        var maximum = ((paramsObj.maximum) || (paramsObj.maximum == 0)) ? paramsObj.maximum : null;
        var is = ((paramsObj.is) || (paramsObj.is == 0)) ? paramsObj.is : null;
        var notANumberMessage = paramsObj.notANumberMessage || ""
        var notAnIntegerMessage = paramsObj.notAnIntegerMessage || "Debe ser un entero!";
        var wrongNumberMessage = paramsObj.wrongNumberMessage || "Debe ser " + is + "!";
        var tooLowMessage = paramsObj.tooLowMessage || "No debe ser minimo " + minimum + "!";
        var tooHighMessage = paramsObj.tooHighMessage || "No debe ser maximo" + maximum + "!";
        if (!isFinite(value))
            Validate.fail(notANumberMessage);
        if (paramsObj.onlyInteger && (/\.0+$|\.$/.test(String(suppliedValue)) || value != parseInt(value)))
            Validate.fail(notAnIntegerMessage);
        switch (true) {
            case (is !== null):
                if (value != Number(is))
                    Validate.fail(wrongNumberMessage);
                break;
            case (minimum !== null && maximum !== null):
                Validate.Numericality(value, {
                    tooLowMessage: tooLowMessage,
                    minimum: minimum
                });
                Validate.Numericality(value, {
                    tooHighMessage: tooHighMessage,
                    maximum: maximum
                });
                break;
            case (minimum !== null):
                if (value < Number(minimum))
                    Validate.fail(tooLowMessage);
                break;
            case (maximum !== null):
                if (value > Number(maximum))
                    Validate.fail(tooHighMessage);
                break;
        }
        return true;
    },
    Format: function (value, paramsObj) {
        var value = String(value);
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        var pattern = paramsObj.pattern || /./;
        var negate = paramsObj.negate || false;
        if (!negate && !pattern.test(value))
            Validate.fail(message); // normal
        if (negate && pattern.test(value))
            Validate.fail(message); // negated
        return true;
    },
    Enteros: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^[0-9]{3,6}$/i
        });
        return true;
    },
    EnterosNA: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        if (value == "N/A") {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^N[/]A$/i
            });
        } else {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^[0-9]{3,6}$/i
            });
        }
        return true;
    },
    Materiales: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        if (value == "N/A") {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^N[/]A$/i
            });
        } else if (value.length <= 7) {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^([a-z0-9]{3,9}){1,1}$/i
            });
        } else {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^([a-z0-9]{3,9}(\-[a-z0-9]{3,9}){1,20})$/i
            });
        }
        return true;
    },
    Enteros2: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^[0-9]{1,3}$/i
        });
        return true;
    },
    Total_numero: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^-?\d*$/i
        });
        return true;
    },
    Decimal: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^[0-9]{1,3}(\.[0-9]{1,3})?$/i
        });
        return true;
    },
    Ficha_tecnica: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^FT-DT-[0-9]{3,4}$/i
        });
        return true;
    },
    Documento: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^[0-9]{7,15}$/i
        });
        return true;
    },
    Email: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})$/i
        });
        return true;
    },
    LoteC: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        if (value == "N/A") {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^N[/]A$/i
            });
        } else {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^[0-9]{1,5}[-][0-9]{1,2}[A-L]{1,1}([0][1-9]|[1-2][0-9]|[3][0-1])$/i
            });
        }
        return true;
    },
    LoteP: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        if (value == "N/A") {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^N[/]A$/i
            });
        } else {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^[0-9]{1,5}[-][0-9]{1,2}[A-L]{1,1}([0][1-9]|[1-2][0-9]|[3][0-1])[-][0-9]{1,2}$/i
            });
        }
        return true;
    },
    LoteCP: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        if (value == "N/A") {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^N[/]A$/i
            });
        } else {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^[0-9]{1,5}[-][0-9]{1,2}[A-L]{1,1}([0][1-9]|[1-2][0-9]|[3][0-1])|[-][0-9]{1,2}$/i
            });
        }
        return true;
    },
    ValorNA: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        if (value == "N/A") {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^N[/]A$/i
            });
        } else {
            Validate.Format(value, {
                failureMessage: message,
                pattern: /^[0-9]{1,3}(\.[0-9]{1,3})?$/i
            });
        }
        return true;
    },
    MP: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^M([0-9]{3,6})$/i
        });
        return true;
    },
    Char: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "Debe ser caracteres!";
        Validate.Format(value, {
            failureMessage: message,
            pattern: /^[a-zA-ZáéíóúAÉÍÓÚñÑ]+([ ]+[a-zA-ZáéíóúAÉÍÓÚñÑ]{0,})*$/i
        });
        return true;
    },
    Length: function (value, paramsObj) {
        var value = String(value);
        var paramsObj = paramsObj || {};
        var minimum = ((paramsObj.minimum) || (paramsObj.minimum == 0)) ? paramsObj.minimum : null;
        var maximum = ((paramsObj.maximum) || (paramsObj.maximum == 0)) ? paramsObj.maximum : null;
        var is = ((paramsObj.is) || (paramsObj.is == 0)) ? paramsObj.is : null;
        var wrongLengthMessage = paramsObj.wrongLengthMessage || "Debe ser " + is + " caracteres!";
        var tooShortMessage = paramsObj.tooShortMessage || "No debe ser minimo " + minimum + " caracteres!";
        var tooLongMessage = paramsObj.tooLongMessage || "No debe ser maximo " + maximum + " caracteres!";
        switch (true) {
            case (is !== null):
                if (value.length != Number(is))
                    Validate.fail(wrongLengthMessage);
                break;
            case (minimum !== null && maximum !== null):
                Validate.Length(value, {
                    tooShortMessage: tooShortMessage,
                    minimum: minimum
                });
                Validate.Length(value, {
                    tooLongMessage: tooLongMessage,
                    maximum: maximum
                });
                break;
            case (minimum !== null):
                if (value.length < Number(minimum))
                    Validate.fail(tooShortMessage);
                break;
            case (maximum !== null):
                if (value.length > Number(maximum))
                    Validate.fail(tooLongMessage);
                break;
            default:
                throw new Error("Validate::Length - Length(s) to validate against must be provided!");
        }
        return true;
    },
    Inclusion: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        var caseSensitive = (paramsObj.caseSensitive === false) ? false : true;
        if (paramsObj.allowNull && value == null)
            return true;
        if (!paramsObj.allowNull && value == null)
            Validate.fail(message);
        var within = paramsObj.within || [];
        //if case insensitive, make all strings in the array lowercase, and the value too
        if (!caseSensitive) {
            var lowerWithin = [];
            for (var j = 0, length = within.length; j < length; ++j) {
                var item = within[j];
                if (typeof item == 'string')
                    item = item.toLowerCase();
                lowerWithin.push(item);
            }
            within = lowerWithin;
            if (typeof value == 'string')
                value = value.toLowerCase();
        }
        var found = false;
        for (var i = 0, length = within.length; i < length; ++i) {
            if (within[i] == value)
                found = true;
            if (paramsObj.partialMatch) {
                if (value.indexOf(within[i]) != -1)
                    found = true;
            }
        }
        if ((!paramsObj.negate && !found) || (paramsObj.negate && found))
            Validate.fail(message);
        return true;
    },
    Exclusion: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        paramsObj.failureMessage = paramsObj.failureMessage || "";
        paramsObj.negate = true;
        Validate.Inclusion(value, paramsObj);
        return true;
    },
    Confirmation: function (value, paramsObj) {
        if (!paramsObj.match)
            throw new Error("Validate::Confirmation - Error validating confirmation: Id of element to match must be provided!");
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        var match = paramsObj.match.nodeName ? paramsObj.match : document.getElementById(paramsObj.match);
        if (!match)
            throw new Error("Validate::Confirmation - There is no reference with name of, or element with id of '" + paramsObj.match + "'!");
        if (value != match.value) {
            Validate.fail(message);
        }
        return true;
    },
    Parametros_maximos: function (value, paramsObj) {
        if (!paramsObj.match)
            throw new Error("Validate::Confirmation - Error validating confirmation: Id of element to match must be provided!");
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        var match = paramsObj.match.nodeName ? paramsObj.match : document.getElementById(paramsObj.match);
        if (!match)
            throw new Error("Validate::Confirmation - There is no reference with name of, or element with id of '" + paramsObj.match + "'!");
        var valor = Number(value);
        var desviacion = Number(match.value);
        if (valor > desviacion) {
            Validate.fail(message);
        }
        return true;
    },
    Parametros_minimos: function (value, paramsObj) {
        if (!paramsObj.match)
            throw new Error("Validate::Confirmation - Error validating confirmation: Id of element to match must be provided!");
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "";
        var match = paramsObj.match.nodeName ? paramsObj.match : document.getElementById(paramsObj.match);
        if (!match)
            throw new Error("Validate::Confirmation - There is no reference with name of, or element with id of '" + paramsObj.match + "'!");
        var valor = Number(value);
        var desviacion = Number(match.value);
        if (valor < desviacion) {
            Validate.fail(message);
        }
        return true;
    },
    Acceptance: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var message = paramsObj.failureMessage || "Debe ser aceptado!";
        if (!value) {
            Validate.fail(message);
        }
        return true;
    },
    Custom: function (value, paramsObj) {
        var paramsObj = paramsObj || {};
        var against = paramsObj.against || function () {
            return true;
        };
        var args = paramsObj.args || {};
        var message = paramsObj.failureMessage || "No es válido!";
        if (!against(value, args))
            Validate.fail(message);
        return true;
    },
    now: function (validationFunction, value, validationParamsObj) {
        if (!validationFunction)
            throw new Error("Validate::now - Validation function must be provided!");
        var isValid = true;
        try {
            validationFunction(value, validationParamsObj || {});
        } catch (error) {
            if (error instanceof Validate.Error) {
                isValid = false;
            } else {
                throw error;
            }
        } finally {
            return isValid
        }
    },
    fail: function (errorMessage) {
        throw new Validate.Error(errorMessage);
    },
    Error: function (errorMessage) {
        this.message = errorMessage;
        this.name = 'ValidationError';
    }
}