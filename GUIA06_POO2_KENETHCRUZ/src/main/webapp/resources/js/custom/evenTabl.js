$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consDato([{name : 'codiPara', value : row.id.trim()}]);
        });
        return false;
    };
    //Eliminar
    $.fn.funcElim = function() {
        $(this).confirmation(
        {
            popout: true,
            onConfirm: function() {
                elimDato();
                $('[data-toggle="confirmation-popout"]').confirmation('hide');
                return false;
            },
            onCancel: function()
            {
                $('[data-toggle="confirmation-popout"]').confirmation('hide');
                return false;
            }
        });
        return false;
    };
    
    INIT_OBJE();
});

function INIT_OBJE()
{
    $("#TablDato").initBootTable();
    $("#FormDato\\:btonElim").funcElim();
}