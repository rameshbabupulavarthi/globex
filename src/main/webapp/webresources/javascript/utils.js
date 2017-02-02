function submitForm() {
            console.log("submit event");
            var fd = new FormData(document.getElementById("fileinfo"));
            fd.append("label", "WEBUPLOAD");
            $.ajax({
              url: "upload.php",
              type: "POST",
              data: fd,
              processData: false,  // tell jQuery not to process the data
              contentType: false   // tell jQuery not to set contentType
            }).done(function( data ) {
                console.log("PHP Output:");
                console.log( data );
            });
            return false;
        }