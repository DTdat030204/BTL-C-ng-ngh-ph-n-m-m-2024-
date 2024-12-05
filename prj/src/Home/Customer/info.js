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
function Home() {

    const navigate = useNavigate();

    const handleHome = () => {
      navigate('/home/customer');
    };
    const CustomerUserID = localStorage.getItem('CustomerUserID');
    function Info() {
        const [info, setInfo] = useState({});
    
        useEffect(() => {
            const fetchInfo = async () => {
                try {
                    const response = await axios.get(`http://localhost:8081/student/${CustomerUserID}/info`);
                    setInfo(response.data); // Update state with the student info
                } catch (error) {
                    console.error("Error fetching student info:", error);
                }
            };
    
            fetchInfo();
        }, []);
    
        return (
            <Box
                sx={{
                    width: '60%', // Set width of the box
                    height: '60vh',
                    fontSize: '1.0rem',
                    color: '#380B61',
                    backgroundImage: 'url("/gradient8.jpg")',
                    backgroundSize: 'cover',
                    borderRadius: '10px',
                    display: 'flex',
                    padding: '20px',
                    mx: 'auto', // Align content horizontally
                    marginTop: '10vh',
                }}
            >
                {/* Left Section: Icon */}
                <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', mr: 3 }}>
                    <img
                        style={{
                            borderRadius: '40px',
                            width: '400px',
                            height: '400px',
                        }}
                        src={  "/ICON_NAM.png"}
                        alt="Icon"
                    />
                    <Typography variant="h6" sx={{ fontWeight: 'bold', mt: 1 }}>
                        Mã sinh viên: {info.studentNumber}
                    </Typography>
                </Box>

                {/* Right Section: Student Info */}
                <Box sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'flex-start', ml: 3 ,textAlign: 'left', marginLeft : '70px' }}>
                    <Typography variant="h4" sx={{ fontWeight: 'bold', color: '#6D41A8', mb: 2, marginTop: '50px', textAlign: 'center' }}>
                        THÔNG TIN SINH VIÊN
                    </Typography>
                    <Typography sx={{ fontWeight: 'bold', fontSize: '1.5rem' }}>
                         Tên: {info.firstName} {info.lastName}
                    </Typography>
                    <Typography sx={{ fontWeight: 'bold', fontSize: '1.5rem' }}>
                         Balance: {info.balance}
                    </Typography>
                    <Typography sx={{ fontWeight: 'bold', fontSize: '1.5rem' }}>
                         Outstanding Amount: {info.outstandingAmount}
                    </Typography>
                    {/* Add more fields as needed */}
                </Box>
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
        <Info/>
    </div>
  );
}

export default Home;


 