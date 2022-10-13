const http = new XMLHttpRequest();

function saveUser() {
    var userName = ($("#username").val() == "" ? "test_username" : $("#username").val())
    var password = ($("#password").val() == "" ? "test123" : $("#password").val())
    var email_form = ($("#email").val() == "" ? "test_email@test.com" : $("#email").val())
    var emails = [{"display": email_form,"primary": true, "type":"work",  "value":email_form}]
    var schemas = ["urn:ietf:params:scim:schemas:core:2.0:User"]
    var rel = { schemas: schemas, userName: userName, password: password, emails: emails }
    $.ajax({
        url: "/v2/Users",
        method: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(rel)
    })
    console.log(JSON.stringify(rel))
}

function editUser(user_id) {
    console.log(JSON.stringify(user_id))
    var myModal = new bootstrap.Modal(document.getElementById('editUserModal'))
    $(".modal-body #id").val( user_id.id );
    $(".modal-body #username_e").val( user_id.userName );
    $(".modal-body #email_e").val( user_id.emails[0].emails.value );
    myModal.show()
}

function saveUserEdition () {
    var userName = ($("#username_e").val() == "" ? "test_username" : $("#username_e").val())
    var email_form = ($("#email_e").val() == "" ? "test_email@test.com" : $("#email_e").val())
    var id = $("#id").val()
    var emails = [{"display": email_form,"primary": true, "type":"work", "value":email_form}]
    var schemas = ["urn:ietf:params:scim:schemas:core:2.0:User"]
    var rel = { schemas: schemas, userName: userName, emails: emails }
    $.ajax({
        url: "/v2/Users/"+id,
        method: "PUT",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(rel)
    })
    console.log(JSON.stringify(rel))
}

function saveMachine() {
    var displayName = ($("#name").val() == "" ? "test_machine" : $("#name").val())
    var machineType = ($("#type").val() == "" ? "local" : $("#type").val())
    var schemas = ["urn:ietf:params:scim:schemas:core:2.0:Machine"]
    var rel = { schemas: schemas, displayName: displayName, MachineType: machineType}
    $.ajax({
        url: "/v2/Machines",
        method: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(rel)
    })
    console.log(JSON.stringify(rel))
}

function editMachine(machine_id) {
    console.log(JSON.stringify(machine_id))
    var myModal = new bootstrap.Modal(document.getElementById('editMachineModal'))
    $(".modal-body #id").val( machine_id.id );
    $(".modal-body #name_e").val( machine_id.displayName );
    $(".modal-body #type_e").val( machine_id.MachineType );
    myModal.show()
}

function saveMachineEdition () {
    var name = ($("#name_e").val() == "" ? "test_machine" : $("#name_e").val())
    var type = ($("#type_e").val() == "" ? "test_local" : $("#type_e").val())
    var id = $("#id").val()
    var schemas = ["urn:ietf:params:scim:schemas:core:2.0:Machine"]
    var rel = { schemas: schemas, displayName: name, MachineType: type}
    $.ajax({
        url: "/v2/Machines/"+id,
        method: "PUT",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(rel)
    })
    console.log(JSON.stringify(rel))
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        saveUser();
        $("#exampleModal").modal("toggle")
    });
    $("#saveMachine").click(function () {
        saveMachine();
        $("#exampleModal").modal("toggle")
    });
    $('#exampleModal').on('hidden.bs.modal', function () {
        location.reload();
    })
    $("#edit").click(function () {
        saveUserEdition();
        $("#editUserModal").modal("toggle")
    });
    $('#editUserModal').on('hidden.bs.modal', function () {
        location.reload();
    })
    $("#editMachine").click(function () {
        saveMachineEdition();
        $("#editMachineModal").modal("toggle")
    });
    $('#editMachineModal').on('hidden.bs.modal', function () {
        location.reload();
    })
});
