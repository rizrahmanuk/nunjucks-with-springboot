import axios from 'axios';
import { Application } from 'express';

export default function (app: Application): void {
  app.get('/get-officers', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const response = await axios.get('http://localhost:8080/case/get-officers');
      console.log(response.data);
      //const policeOfficersJSon: string  = JSON.stringify(response.data);
      console.log(response.data);
      res.render('police-officers', { police_officers: response.data });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('home', {});
    }
  });

  app.get('/', async (req, res) => {
    try {
      res.render('add-user-details');
    } catch (error) {
      console.error('Error making request:', error);
      res.render('home', {});
    }
  });
}
