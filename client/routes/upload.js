const express = require('express');
const multer = require('multer');
<<<<<<< HEAD

const request = require('request');
=======
>>>>>>> b1c578a... Client side
const router = express.Router();

const upload = multer({ dest: './uploads/' });

router.get('/upload', (req, res, next) => {
  res.render('upload');
});

router.post('/upload', upload.single('myFile'), (req, res) => {
  if (req.file) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> d6c5777... working upload
    let objJsonStr = JSON.stringify(req.file);
    let objJsonB64 = Buffer.from(objJsonStr).toString('base64');
    console.log(objJsonB64);

<<<<<<< HEAD
=======
>>>>>>> b1c578a... Client side
=======
>>>>>>> d6c5777... working upload
    console.log('Uploading file...');
    var filename = req.file.filename;
    var uploadStatus = 'File Uploaded Successfully';
  } else {
    console.log('No File Uploaded');
    var filename = 'FILE NOT UPLOADED';
    var uploadStatus = 'File Upload Failed';
  }

  /* ===== Add the function to save filename to database ===== */

<<<<<<< HEAD

=======
>>>>>>> b1c578a... Client side
  res.render('index.ejs', {
    status: uploadStatus,
    filename: `Name Of File: ${filename}`
  });
<<<<<<< HEAD

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
=======
>>>>>>> b1c578a... Client side
});

module.exports = router;
