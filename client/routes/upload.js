const express = require('express');
const multer = require('multer');
const router = express.Router();

const upload = multer({ dest: './uploads/' });

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

  res.render('index.ejs', {
    status: uploadStatus,
    filename: `Name Of File: ${filename}`
  });
});

module.exports = router;
