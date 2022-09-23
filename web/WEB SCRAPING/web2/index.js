// https://ums.lpu.in/lpuums/LoginNew.aspx
// get

const superagent = require('superagent').agent()

const ytm = async () => {
    let dashboard = await superagent
        .get('https://ums.lpu.in/lpuums/LoginNew.aspx')
        .send({ username: '12103196', password: 'Meena@121' })
        .set('Content-type','text/html; charset=utf-8');
    console.log(dashboard.text);
};

ytm();