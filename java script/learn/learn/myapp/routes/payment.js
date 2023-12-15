var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
    res.render('payment', { ment: '78' });
});

module.exports = router;