var express = require('express');
var app = express();


var db = [
  {
    id: 'vmc01',
    status: 'Huy du lieu 01'
  },
  {
    id: 'vmc02',
    status: 'Hoat dong 02'
  },
  {
    id: 'vmc03',
    status: 'Huy du lieu 03'
  },
  {
    id: 'vmc04',
    status: 'Hoat dong 04'
  }
];

app.get('/', function (req, res) {
   res.send('Hello World');
})



app.get('/huyservice', getIdDevices,function (req, res) {
  res.json(db);
});

app.get('/huyservice/:id', getIdDevices,function (req, res) {});


app.get('/otaservice', function (req, res) {
  res.send('Hello World');
})

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Ung dung Node.js dang lang nghe tai dia chi: http://%s:%s", host, port)

})

async function getIdDevices(req, res, next){
  var id = req.params.id;
  var matchedTodo;
  db.forEach(function(i){
    if(id==i.id){
      matchedTodo = i.status;
      console.log(matchedTodo);
    }
  });
  if (matchedTodo) {
    res.json(matchedTodo);
  } else {
    res.status(404).send();
  }
  next()
}