import axios from 'axios';
import { Application } from 'express';

export default function (app: Application): void {
  module.require('axios');

  app.get('/get-case/:id', async (req, res) => {
    const caseId: string = req.params.id;
    try {
      // const userCase: string = req.data;
      // const userCase: string = "{'id': 121200, 'caseNumber': '1', 'title': 'Update case details'," +
      //   " 'description': 'user failed to attend interview'," +
      //   " 'status': 'UNDER-REVIEW'," +
      //   " 'createdDate': '02/05/2025'" +
      //   " }";
      // An example of connecting to the backend (a starting point)
      const response = await axios.get('http://localhost:8080/case/get-case/' + caseId);
      // console.log(response.data);
      res.render('update-case', { case: response.data, status: response.status });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('update-case', {});
    }
  });

  // This should be a PUT not being called, only works via PostMan
  app.post('/update-case', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const data: string = req.body;
      const response = await axios.put('http://localhost:8080/case/update-case/1', data);

      console.log(response.data);
      res.render('readonly-case', {
        case: response.data,
        status: response.status,
        savedCase: true,
        header: 'Case updated',
      });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('error', { error: error.message });
    }
  });

  app.post('/add-new-case', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const data = req.body;
      //const response = await axios.post('http://localhost:8080/case/create-case', data);
      const json: string = JSON.stringify(req.body);
      console.log(data);
      res.render('add-new-case', { policeOfficer: json, header: 'Add first case' });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('error', { error: error.message });
    }
  });

  app.post('/add-user-details', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const data: string = req.body;
      //const response = await axios.post('http://localhost:8080/case/create-case', data);

      console.log(data);
      res.render('add-user-details', { case: JSON.stringify(data), header: 'Add user details' });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('error', { error: error.message });
    }
  });

  app.post('/persist-case', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const dataString: string = JSON.stringify(req.body);
      const data2: string = dataString.replace('"{', '{').replace('}"', '}');
      const data3: string = data2.replace(/\\"/g, '"');
      const data: object = JSON.parse(data3);
      const response = await axios.post('http://localhost:8080/case/create-case', data);

      console.log(response.data);
      res.render('add-user-details', { case: response.data, header: 'Add user details' });
    } catch (error) {
      console.error('Error making request:', error);
      res.render('error', { error: error.message });
    }
  });

  app.post('/confirm-delete/:id', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const caseId: string = req.params.id;
      const response = await axios.get('http://localhost:8080/case/get-case/' + caseId);

      console.log(response.data);
      res.render('readonly-case', { case: response.data, action: '/delete-case/' + caseId, header: 'Confirm delete' });
    } catch (error) {
      console.error('Error making delete request:', error);
      res.render('error', { error: error.message });
    }
  });

  app.post('/delete-case/:id', async (req, res) => {
    try {
      // An example of connecting to the backend (a starting point)
      const caseId: string = req.params.id;
      const response = await axios.delete('http://localhost:8080/case/delete-case/' + caseId);

      console.log(response.data);
      res.render('readonly-case', { case: response.data, header: 'Successfully deleted case' });
    } catch (error) {
      console.error('Error making delete request:', error);
      res.render('error', { error: error.message });
    }
  });
}
