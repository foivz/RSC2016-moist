$('.modal').modal({
    dismissible: true, // Modal can be dismissed by clicking outside of the modal
    opacity: .5, // Opacity of modal background
    in_duration: 300, // Transition in duration
    out_duration: 200, // Transition out duration
    starting_top: '4%', // Starting top style attribute
    ending_top: '10%', // Ending top style attribute
    ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
      console.log(modal, trigger);
    },
    complete: function() { 
      console.log('Closed');
    } // Callback for Modal close
  }
);

var baseUrl = "api/";

$('#btnSubmitData').click(function(e) {
    var name = $('#name');
    var date = $('#date');
    var time = $('#time');
    var description = $('#description');
    var prizes = $('prizes');
    var rules = $('rules');

    var endPoint = baseUrl + "quiz/";

    e.preventDefault();

    var data = {
      "name": name,
      "latitude": 46.3076267,
      "longitude": 16.3363742,
      "date": date,
      "time": time,
      "description": description,
      "prizes": prizes,
      "rues": rules
    }

    var token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdlYjU1Y2QxOTBkOTM0NzdlNjlmYTg5NzQ0NjNmZGE1YzZjNTdlZmIzYzRkODA5ZGQ3ZDZlNmI5ZjBhNWI2NDFmMWQ1ZDA5NDNiMDM2MzAzIn0.eyJhdWQiOiIxIiwianRpIjoiN2ViNTVjZDE5MGQ5MzQ3N2U2OWZhODk3NDQ2M2ZkYTVjNmM1N2VmYjNjNGQ4MDlkZDdkNmU2YjlmMGE1YjY0MWYxZDVkMDk0M2IwMzYzMDMiLCJpYXQiOjE0ODAxNzExNzYsIm5iZiI6MTQ4MDE3MTE3NiwiZXhwIjoxNTExNzA3MTc2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.An0UbVEEIJ9IDsn0dmpHPQWX8zAx4OXd_fVo-xQEQej9II1SKrQ92JJKTb1iDpEthXE2V07ZQIcSP0wFtiPyHVi1th6UWUE3vS2C58Ruw41ysw_dUIlQ40LCxCtvQneRiBC8hOsDG5NClMI2cgIgTSSsB5EuVBQsJRJptkf8IRb5je4324bZQqeu5iAkmEnVVkjE9DJsZxGdgg2z1sxOk_N68u8Q5dTTeIFxwWyiWPusrUF6TLlGXnUsMdNqZlWNWMWgFGwNrtyVqvv9K8rR3bxFhckiU4vu7AxGivWtTEFGmZJ1DBfcqzJuQu3SWv-6VRJmebV5iUzWybutsSsNbvJn1MntSM5tquYInck9pALqV9tylvV0uPUjGHzv7dVOnPDN6ynf2hC4YRXO4H9ZpMx6IfR3vuu5HAHy73fhUy7M3TI_AHpkV-sJCzkdArSsHtl3_zvL5JlwHJiCWv59tvZ_5-FpRNpTWIdpDxSMNpr1UzeDfB-KSeKIXZK4vkcsyPxo5sUCpVYUGzcGWZfljaq3hGMe9wyG3hFeHoOoqBxN02-mTPm_Yb8r2HIubBTzelVUey2TOSA38-Sw-xDSUTSzA3NTheHEHRelKFXvC1m98LPMkSo6J_n_K9WCXNs-e1pQEwiHEQPOkVDrIVQ0XDBQb7HoLW2F7AlIxdFw0nU";

    $.ajax({
      url: endPoint,
      headers: {
        'Authorization': token
      },
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(data),
      dataType: 'json',
      success: function(data) {

      },
      error: function(xhr, ajaxOptions, thrownError) {
        if (xhr.status == 200) {
          alert(ajaxOptions);
        }
        else {
          alert(xhr.status);
          alert(thrownError);
        }
      }
    })
})