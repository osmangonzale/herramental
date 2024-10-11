var TinyMCE_SimpleTheme = {
    // List of button ids in tile map
    _defColors: "000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,008000,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF",
    _autoImportCSSClasses: true,
    _resizer: {},
    _buttons: [
        // Control id, button img, button title, command, user_interface, value
        ['bold', '{$lang_bold_img}', 'lang_bold_desc', 'Bold'],
        ['italic', '{$lang_italic_img}', 'lang_italic_desc', 'Italic'],
        ['underline', '{$lang_underline_img}', 'lang_underline_desc', 'Underline'],
        ['strikethrough', 'strikethrough.gif', 'lang_striketrough_desc', 'Strikethrough'],
        ['justifyleft', 'justifyleft.gif', 'lang_justifyleft_desc', 'JustifyLeft'],
        ['justifycenter', 'justifycenter.gif', 'lang_justifycenter_desc', 'JustifyCenter'],
        ['justifyright', 'justifyright.gif', 'lang_justifyright_desc', 'JustifyRight'],
        ['justifyfull', 'justifyfull.gif', 'lang_justifyfull_desc', 'JustifyFull'],
        ['bullist', 'bullist.gif', 'lang_bullist_desc', 'InsertUnorderedList'],
        ['numlist', 'numlist.gif', 'lang_numlist_desc', 'InsertOrderedList'],
        ['outdent', 'outdent.gif', 'lang_outdent_desc', 'Outdent'],
        ['indent', 'indent.gif', 'lang_indent_desc', 'Indent'],
        ['cut', 'cut.gif', 'lang_cut_desc', 'Cut'],
        ['copy', 'copy.gif', 'lang_copy_desc', 'Copy'],
        ['paste', 'paste.gif', 'lang_paste_desc', 'Paste'],
        ['undo', 'undo.gif', 'lang_undo_desc', 'Undo'],
        ['redo', 'redo.gif', 'lang_redo_desc', 'Redo'],
        ['link', 'link.gif', 'lang_link_desc', 'mceLink', true],
        ['unlink', 'unlink.gif', 'lang_unlink_desc', 'unlink'],
        ['image', 'image.gif', 'lang_image_desc', 'mceImage', true],
        ['cleanup', 'cleanup.gif', 'lang_cleanup_desc', 'mceCleanup'],
        ['help', 'help.gif', 'lang_help_desc', 'mceHelp'],
        ['code', 'code.gif', 'lang_theme_code_desc', 'mceCodeEditor'],
        //['hr', 'hr.gif', 'lang_theme_hr_desc', 'inserthorizontalrule'],
        //['removeformat', 'removeformat.gif', 'lang_theme_removeformat_desc', 'removeformat'],
        ['sub', 'sub.gif', 'lang_theme_sub_desc', 'subscript'],
        ['sup', 'sup.gif', 'lang_theme_sup_desc', 'superscript'],
        ['forecolor', 'forecolor.gif', 'lang_theme_forecolor_desc', 'forecolor', true],
        ['backcolor', 'backcolor.gif', 'lang_theme_backcolor_desc', 'HiliteColor', true],
        ['charmap', 'charmap.gif', 'lang_theme_charmap_desc', 'mceCharMap'],
        //['visualaid', 'visualaid.gif', 'lang_theme_visualaid_desc', 'mceToggleVisualAid'],
        ['anchor', 'anchor.gif', 'lang_theme_anchor_desc', 'mceInsertAnchor'],
        ['newdocument', 'newdocument.gif', 'lang_newdocument_desc', 'mceNewDocument']
    ],
    _buttonMap: 'bold,bullist,cleanup,italic,numlist,redo,strikethrough,underline,undo,backcolor,image,unlink',
    getEditorTemplate: function () {
        var html = '';

        html += '<table class="mceEditor" border="0" cellpadding="0" cellspacing="0" width="{$width}" height="{$height}">';
        html += '<tr><td align="center">';
        html += '<span id="{$editor_id}">IFRAME</span>';
        html += '</td></tr>';
        html += '<tr><td class="mceToolbar" align="center" height="1">';
        html += tinyMCE.getButtonHTML('bold', 'lang_bold_desc', '{$themeurl}/images/{$lang_bold_img}', 'Bold');
        html += tinyMCE.getButtonHTML('italic', 'lang_italic_desc', '{$themeurl}/images/{$lang_italic_img}', 'Italic');
        html += tinyMCE.getButtonHTML('underline', 'lang_underline_desc', '{$themeurl}/images/{$lang_underline_img}', 'Underline');
        html += tinyMCE.getButtonHTML('strikethrough', 'lang_striketrough_desc', '{$themeurl}/images/strikethrough.gif', 'Strikethrough');
        html += '<img src="{$themeurl}/images/separator.gif" width="2" height="20" class="mceSeparatorLine" />';
        html += tinyMCE.getButtonHTML('undo', 'lang_undo_desc', '{$themeurl}/images/undo.gif', 'Undo');
        html += tinyMCE.getButtonHTML('redo', 'lang_redo_desc', '{$themeurl}/images/redo.gif', 'Redo');
        html += '<img src="{$themeurl}/images/separator.gif" width="2" height="20" class="mceSeparatorLine" />';
        html += tinyMCE.getButtonHTML('cleanup', 'lang_cleanup_desc', '{$themeurl}/images/cleanup.gif', 'mceCleanup');
        html += '<img src="{$themeurl}/images/separator.gif" width="2" height="20" class="mceSeparatorLine" />';
        html += tinyMCE.getButtonHTML('bullist', 'lang_bullist_desc', '{$themeurl}/images/bullist.gif', 'InsertUnorderedList');
        html += tinyMCE.getButtonHTML('numlist', 'lang_numlist_desc', '{$themeurl}/images/numlist.gif', 'InsertOrderedList');
        html += '</td></tr></table>';

        return {
            delta_width: 0,
            delta_height: 20,
            html: html
        };
    },
    handleNodeChange: function (editor_id, node) {
        // Reset old states
        tinyMCE.switchClass(editor_id + '_bold', 'mceButtonNormal');
        tinyMCE.switchClass(editor_id + '_italic', 'mceButtonNormal');
        tinyMCE.switchClass(editor_id + '_underline', 'mceButtonNormal');
        tinyMCE.switchClass(editor_id + '_strikethrough', 'mceButtonNormal');
        tinyMCE.switchClass(editor_id + '_bullist', 'mceButtonNormal');
        tinyMCE.switchClass(editor_id + '_numlist', 'mceButtonNormal');

        // Handle elements
        do {
            switch (node.nodeName.toLowerCase()) {
                case "b":
                case "strong":
                    tinyMCE.switchClass(editor_id + '_bold', 'mceButtonSelected');
                    break;

                case "i":
                case "em":
                    tinyMCE.switchClass(editor_id + '_italic', 'mceButtonSelected');
                    break;

                case "u":
                    tinyMCE.switchClass(editor_id + '_underline', 'mceButtonSelected');
                    break;

                case "strike":
                    tinyMCE.switchClass(editor_id + '_strikethrough', 'mceButtonSelected');
                    break;

                case "ul":
                    tinyMCE.switchClass(editor_id + '_bullist', 'mceButtonSelected');
                    break;

                case "ol":
                    tinyMCE.switchClass(editor_id + '_numlist', 'mceButtonSelected');
                    break;
            }
        } while ((node = node.parentNode) != null);
    }
};

tinyMCE.addTheme("simple", TinyMCE_SimpleTheme);
tinyMCE.addButtonMap(TinyMCE_SimpleTheme._buttonMap);
