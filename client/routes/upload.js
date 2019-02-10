const express = require('express');
const multer = require('multer');
const request = require('request');
const router = express.Router();

const upload = multer({ dest: './uploads/' });

// var print = {
//   fingerPrintImage:
//     'eyJmaWVsZG5hbWUiOiJteUZpbGUiLCJvcmlnaW5hbG5hbWUiOiJuYW1la0xvZ28uanBnIiwiZW5jb2RpbmciOiI3Yml0IiwibWltZXR5cGUiOiJpbWFnZS9qcGVnIiwiZGVzdGluYXRpb24iOiIuL3VwbG9hZHMvIiwiZmlsZW5hbWUiOiIwNzdjNWYyYjNiNDkxYmIxNDMzMjNlYTBlM2Q2ZGMwMiIsInBhdGgiOiJ1cGxvYWRzLzA3N2M1ZjJiM2I0OTFiYjE0MzMyM2VhMGUzZDZkYzAyIiwic2l6ZSI6MzExMTV9',
//   passportImage: 'toto'
// };

router.get('/upload', (req, res, next) => {
  res.render('upload');
});

router.post('/upload', upload.single('myFile'), (req, res) => {
  if (req.file) {
    let objJsonStr = JSON.stringify(req.file);
    let objJsonB64 = Buffer.from(objJsonStr).toString('base64');
    console.log(objJsonB64);

    console.log('Uploading file...');
    var filename = req.file.filename;
    var uploadStatus = 'File Uploaded Successfully';
  } else {
    console.log('No File Uploaded');
    var filename = 'FILE NOT UPLOADED';
    var uploadStatus = 'File Upload Failed';
  }

  /* ===== Add the function to save filename to database ===== */

  // res.render('index.ejs', {
  //   status: uploadStatus,
  //   filename: `Name Of File: ${filename}`
  // });
  console.log('SUCCESS');
  // request.post('http://localhost:8084/api/storeUserFingerPrintInformations', {
  //   json: {
  //     fingerPrintImage: objJsonB64,
  //     passportImage: 'toto'
  //   }
  // });

  var options = {
    host: 'http://localhost:8084',
    path: '/api/storeUserFingerPrintInformations',
    method: 'POST'
  };

  var req = http.request(options, function(res) {
    var responseString = '';

    res.on('data', function(data) {
      responseString += data;
      // save all the data from response
    });
    res.on('end', function() {
      console.log(responseString);
      // print to console when response ends
    });
  });
  var reqBody = {
    fingerPrintImage: objJsonB64,
    passportImage: 'toto'
  };
  req.write(reqBody);
});

module.exports = router;
