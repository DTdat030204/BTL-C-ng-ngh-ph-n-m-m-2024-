import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import SendIcon from '@mui/icons-material/Send'
import Box from '@mui/material/Box'
import Typography from '@mui/material/Typography'
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import React, { useState, useEffect } from 'react';
import LoginIcon from '@mui/icons-material/Login';
import SchoolIcon from '@mui/icons-material/School';
import TextField from '@mui/material/TextField';
import {useNavigate}  from 'react-router-dom';
import { styled} from '@mui/material';
import axios from 'axios'; 
import IconButton from '@mui/material/IconButton';
import InputAdornment from '@mui/material/InputAdornment';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import NotificationsIcon from '@mui/icons-material/Notifications';
import MessageIcon from '@mui/icons-material/Message'; // Biểu tượng tin nhắn
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import {  Grid, Card,CardContent, CardMedia } from '@mui/material';
import NoteIcon from '@mui/icons-material/Note';
import {  Popover, List, ListItem, ListItemText } from '@mui/material';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
function Home() {

    const navigate = useNavigate();

    const handleHome = () => {
      navigate('/home/customer');
    };
    const CustomerUserID = localStorage.getItem('CustomerUserID');
    function PaymentLog() {
        const [paymentLog, setPaymentLog] = useState([]);
    
        useEffect(() => {
            const fetchInfo = async () => {
                try {
                    const response = await axios.get(`http://localhost:8081/student/${CustomerUserID}/info`);
                    // const response = await axios.get('http://localhost:8081/student/67458c83fb4e2e0588eb4aa5/info');
                    setPaymentLog(response.data.paymentLogs || []); // Update state with the printing logs
                } catch (error) {
                    console.error("Error fetching printing logs:", error);
                }
            };
    
            fetchInfo();
        }, []);
    
        return (
            <Box sx={{ width: '80%', mx: 'auto', mt: '5vh', p: 2, backgroundColor: '#F5F5F5', borderRadius: '10px' }}>
                <Typography variant="h4" sx={{ mb: 3, textAlign: 'center', color: '#6D41A8' }}>
                    LỊCH SỬ MUA GIẤY
                </Typography>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell align="center"><strong>Số lượng mua</strong></TableCell>
                                <TableCell align="center"><strong>Đơn tiền </strong></TableCell>
                                <TableCell align="center"><strong>Ngày giao dịch </strong></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {paymentLog.map((log) => (
                                <TableRow key={log.id}>
                                    <TableCell align="center">{log.numOfPages}</TableCell>
                                    <TableCell align="center">{log.unitPrice + ' VNĐ'}</TableCell>
                                    <TableCell align="center">{new Date(log.payDate).toLocaleString()}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        );
    }

  return (
    <div className="App" style={{ 
        // backgroundImage: 'url("/img2.jpg")', // Sử dụng đường dẫn từ thư mục public
        backgroundColor: '#F5F5F5',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        minHeight: '100vh', // Sử dụng minHeight thay vì height
        display: 'flex',     // Sử dụng flexbox để tự động điều chỉnh chiều cao
        flexDirection: 'column', // Căn chỉnh theo chiều dọc
      }}>   

        <AppBar position="static">
            <Toolbar sx={{ bgcolor: '#66B2FF', display: 'flex', justifyContent: 'space-between' }}>
                <Button onClick ={handleHome}>
                    <img src="/Avatar.png" alt="Icon" width="80" height="40" />
                    <Typography variant='h5' style={{ fontWeight: 'bold' }} align='left' sx={{ flexGrow: 1 }} color='#FFFFFF'>
                        Student Smart Printing Service
                    </Typography>
                </Button>
            </Toolbar>
        </AppBar>
        <PaymentLog/>
    </div>
  );
}

export default Home;


 