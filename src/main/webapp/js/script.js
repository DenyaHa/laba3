var allRecords = null;
	$(document).ready(function () {
        makeUpdate();

	$("#search-form").submit(function (event) {
		event.preventDefault();

		var value = document.getElementById("search-input").value;
		for(var i = 0; i < allRecords.length; i++) {

            if (allRecords[i].company == value || allRecords[i].mail == value || allRecords[i].url == value)
                $("#"+"r" + allRecords[i].id).css("display", "flex");
            else
                $("#"+"r" + allRecords[i].id).css("display", "none");
        }

	});

	$("#add-form").submit(function (event) {
        event.preventDefault();
        var c = "";
        var m = "";
        var u = "";
        c += document.getElementById("cname-a").value;
        m += document.getElementById("mail-a").value;
        u += document.getElementById("url-a").value;

        $.post('/records', {'type':'ADD', 'company': c, 'mail': m, 'url': u},
            function (data) {
                makeUpdate();
            });
	});

	$("#result").on("click",function(event){
        event.preventDefault();

        var name = event.target.id.replace(/\d+/g, '');

        if(name == "delete-btn") {
            var id = event.target.id.replace(/[^0-9]/gim, "");

            $.post('/records', {'type':'DELETE', 'id': event.target.id.replace(/[^0-9]/gim, "")},
                function (data) {
                    makeUpdate();
                });
        }
        else if(name == "change-btn") {
            var id = event.target.id.replace(/[^0-9]/gim, "");
            var c = "";
            var m = "";
            var u = "";
            c += document.getElementById("cname"+id).value;
            m += document.getElementById("mail"+id).value;
            u += document.getElementById("url"+id).value;


            $.post('/records', {'type':'CHANGE', 'id': event.target.id.replace(/[^0-9]/gim, ""), 'company': c, 'mail': m, 'url': u},
                function (data) {
                    makeUpdate();
                });
		}

	});

	$("#clear-btn").click(function (event) {
        event.preventDefault();
        $.post('/records', {'type':'CLEAR'},
            function (data) {
				makeUpdate()
            });
	});
});

function update(data) {
    $('#result').html("");
    for(var i = 0; i < data.length; i++) {
        $('#result').append("<form id=\"" +
            "r"+data[i].id +
            "\" class=\"form-inline record-form\">\n" +
            "            <label for=\"cname" +
            data[i].id +
            "\" class=\"mr-sm-2\">Company:</label>\n" +
            "            <input value=\"" +
            data[i].company +
            "\" type=\"text\" class=\"form-control mb-2 mr-sm-2\" placeholder=\"Enter company name\" id=\"cname" +
            data[i].id +
            "\">\n" +
            "            <label for=\"mail" +
            data[i].id +
            "\" class=\"mr-sm-2\">Email:</label>\n" +
            "            <input value=\"" +
            data[i].mail +
            "\" type=\"email\" class=\"form-control mb-2 mr-sm-2\" placeholder=\"Enter email\" id=\"mail" +
            data[i].id +
            "\">\n" +
            "            <label for=\"url" +
            data[i].id +
            "\" class=\"mr-sm-2\">Url:</label>\n" +
            "            <input value=\"" +
            data[i].url +
            "\" type=\"text\" class=\"form-control mb-2 mr-sm-2\" placeholder=\"Enter url\" id=\"url" +
            data[i].id +
            "\">\n" +
            "            <button id=\"change-btn" +
            data[i].id +
            "\" class=\"btn btn-primary c-btn mb-2 mr-1\">Change</button>\n" +
            "            <button id=\"delete-btn" +
            data[i].id +
            "\" class=\"btn btn-danger d-btn mb-2\">Delete</button>\n" +
            "        </form>");
    }
};

function makeUpdate() {
    $.post('/records', {'type': 'UPDATE'},
        function (data) {
            var response = JSON.parse(data);
            update(response);

            allRecords = response;
        });
}