/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global fetch */

function loadCursos() {
    var selectedEstudiante = $("#txtEstudiante").val();
    $.ajax({
        url: 'ControllerUniversidad',
        method: 'POST',
        data: 'accion=loadCursos&txtEstudiante=' + selectedEstudiante,
        dataType: 'html',
        success: function (data) {
            $('#txtCurso').html(data);
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }
    });
}
