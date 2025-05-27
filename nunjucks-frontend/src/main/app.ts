import * as path from 'path';

import { HTTPError } from './HttpError';
import { Nunjucks } from './modules/nunjucks';

import * as bodyParser from 'body-parser';
import cookieParser from 'cookie-parser';
import express from 'express';
import { glob } from 'glob';
import favicon from 'serve-favicon';

const { setupDev } = require('./development');

const env = process.env.NODE_ENV || 'development';
const developmentMode = env === 'development';

export const app = express();
app.locals.ENV = env;

new Nunjucks(developmentMode).enableFor(app);

app.use(favicon(path.join(__dirname, '/public/assets/images/favicon.ico')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// app.use((req, res, next) => {
//   res.header('Cache-Control', 'no-cache, max-age=0, must-revalidate, no-store');
//   req.header('Acces-Control-Allow-Methods="GET, PUT, POST, DELETE, PATCH, OPTIONS"');
//   next();
// });

app.use(function (req, res, next) {
  res.header('Access-Control-Allow-Origin', 'https://localhost:3100');
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
  res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE, OPTIONS');
  req.headers['access-control-allow-methods'] = 'get, put, post, delete';
  next();
});
// app.use((req, res, next)=> {
//   res.locals.pagePath = req.path;
//   res.header("Access-Control-Allow-Origin", "*");
//   res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//   res.header("Acces-Control-Allow-Methods", "GET, PUT, POST, DELETE");
//     return res.status(200).json({});
// });

glob
  .sync(__dirname + '/routes/**/*.+(ts|js)')
  .map(filename => require(filename))
  .forEach(route => route.default(app));

setupDev(app, developmentMode);

//error handler
app.use((err: HTTPError, req: express.Request, res: express.Response) => {
  console.log(err);
  //set locals, only providing error in development
  // res.locals.message = err.message;
  res.locals.error = env === 'development' ? err : {};
  res.status(err.status || 500);
  res.render('error');
});
