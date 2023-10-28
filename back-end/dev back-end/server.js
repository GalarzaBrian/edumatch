const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()
const router = jsonServer.router('./db.json')
const userdb = JSON.parse(fs.readFileSync('./users.json', 'UTF-8'))

server.use(bodyParser.urlencoded({extended: true}))
server.use(bodyParser.json())
server.use(jsonServer.defaults());

const SECRET_KEY = '123456789'

const expiresIn = '1h'

// Create a token from a payload 
function createToken(payload){
  return jwt.sign(payload, SECRET_KEY, {expiresIn})
}

// Verify the token 
function verifyToken(token){
  return  jwt.verify(token, SECRET_KEY, (err, decode) => decode !== undefined ?  decode : err)
}

// Check if the user exists in database
function isAuthenticated({email, password}){
  return userdb.users.findIndex(user => user.email === email && user.password === password) !== -1
}

// Register New User
server.post('/auth/register', (req, res) => {
  console.log("register endpoint called; request body:");
  console.log(req.body);
  const {dni,email, password,roleId} = req.body;

  if(isAuthenticated({email, password}) === true) {
    const status = 401;
    const message = 'Email ya existe';
    res.status(status).json({status, message});
    return
  }

fs.readFile("./users.json", (err, data) => {  
    if (err) {
      const status = 401
      const message = err
      res.status(status).json({status, message})
      return
    };

    // Get current users data
    var data = JSON.parse(data.toString());

    // Get the id of last user
    var last_item_id = data.users[data.users.length-1].id;

    //Add new user
    data.users.push({id: last_item_id + 1,dni: dni, email: email, password: password, roleId:roleId}); //add some data
    var writeData = fs.writeFile("./users.json", JSON.stringify(data), (err, result) => {  // WRITE
        if (err) {
          const status = 401
          const message = err
          res.status(status).json({status, message})
          return
        }
    });
});

// Create token for new user
  const access_token = createToken({email, password})
  console.log("email: " + email, "jwt:" + access_token);
  res.status(200).json({"email":email, "jwt": access_token})
})

// Login to one of the users from ./users.json
server.post('/auth/login', (req, res) => {
  console.log("login endpoint called; request body:");
  console.log(req.body);
  const {email, password} = req.body;
  if (isAuthenticated({email, password}) === false) {
    const status = 401
    const message = 'Email o password incorrecto'
    res.status(status).json({status, message})
    return
  }
  const access_token = createToken({email, password})
  console.log("jwt:" + access_token);
  res.status(200).json({"email":email, "jwt": access_token})
})

server.use(/^(?!\/auth).*$/,  (req, res, next) => {
  if (req.headers.authorization === undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Error en formato de autorizacion'
    res.status(status).json({status, message})
    return
  }
  try {
    let verifyTokenResult;
     verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

     if (verifyTokenResult instanceof Error) {
       const status = 401
       const message = 'Sin token de acceso'
       res.status(status).json({status, message})
       return
     }
     next()
  } catch (err) {
    const status = 401
    const message = 'Token expirado'
    res.status(status).json({status, message})
  }
})
server.get('/auth/me', (req, res) => {
  if (req.headers.authorization === undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Error en formato de autorizacion'
    res.status(status).json({status, message})
    return
  }
  
     try {
      let verifyTokenResult;
      verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);
      console.log(verifyTokenResult.email)
      let userEmail = verifyTokenResult.email
      verifyTokenResult
      userFind = userdb.users.findIndex((user)=> user.email == userEmail)

      const status = 200
      const dni= userdb.users[userFind].dni
      const email = userdb.users[userFind].email
      res.status(status).json({"dni: ":dni, "mail":email})
    
    } catch (error) {
      const status = 404
    const message = 'Usuario no encontrado'
    res.status(status).json({status, message})
    }
})

server.use(router)

server.listen(8080, () => {
  console.log('Corriendo Dev Server de Edumatch')
})