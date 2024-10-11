var startDate2,
        endDate2,
        updateStartDate2 = function() {
            startPicker2.setStartRange(startDate2);
            endPicker2.setStartRange(startDate2);
            endPicker2.setMinDate(startDate2);
        },
        updateEndDate2 = function() {
            startPicker2.setEndRange(endDate2);
            startPicker2.setMaxDate(endDate2);
            endPicker2.setEndRange(endDate2);
        },
        startPicker2 = new Pikaday({
            field: document.getElementById('start2'),
            maxDate: new Date(2050, 12, 31),
            onSelect: function() {
                startDate2 = this.getDate();
                updateStartDate2();
            }
        }),
        endPicker2 = new Pikaday({
            field: document.getElementById('end2'),
            maxDate: new Date(2050, 12, 31),
            onSelect: function() {
                endDate2 = this.getDate();
                updateEndDate2();
            }
        }),
        _startDate2 = startPicker2.getDate(),
        _endDate2 = endPicker2.getDate();

        if (_startDate2) {
            startDate2 = _startDate2;
            updateStartDate2();
        }

        if (_endDate2) {
            endDate2 = _endDate2;
            updateEndDate2();
        }


