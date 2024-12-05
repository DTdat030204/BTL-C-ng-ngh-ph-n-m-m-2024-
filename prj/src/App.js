
import './App.css';

import { BrowserRouter, Routes, Route } from  'react-router-dom'
import Home from './Home/home';
import Initial from  './Home/initial';

import Admin from './Home/Admin/admin';

import Customer from './Home/Customer/customer';
import Info from './Home/Customer/info';
import PrintingLog from './Home/Customer/printingLog';
import PaymentLog from './Home/Customer/paymentLog';
import Print from './Home/Customer/print';
function App() {
  return (
    <BrowserRouter>
      < Routes>
        <Route path='/home' exact element={<Home />} />


        <Route path='/home/admin' exact element={<Admin />} />

        <Route path='/home/customer' exact element={<Customer />} />
        <Route path='/home/customer/info' exact element={<Info />} />
        <Route path='/home/customer/printingLog' exact element={<PrintingLog />} />
        <Route path='/home/customer/paymentLog' exact element={<PaymentLog />} />
        <Route path='/home/customer/print/:id' exact element={<Print />} />
        <Route path='/initial' exact element={<Initial />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;
