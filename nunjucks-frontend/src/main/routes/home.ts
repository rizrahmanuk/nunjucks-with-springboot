import axios from 'axios';
import { Application } from 'express';

export default function (app: Application): void {
  app.get('/get-cases', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const response = await axios.get('http://localhost:8080/case/get-cases');
      console.log(response.data);
      res.render('home', { cases: response.data });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('home', {});
    }
  });
}
