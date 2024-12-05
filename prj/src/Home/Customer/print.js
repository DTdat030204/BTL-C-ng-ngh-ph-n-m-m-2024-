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
import {useNavigate,useParams}  from 'react-router-dom';
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
import { Checkbox, FormControlLabel,  Select, InputLabel, FormControl } from '@mui/material';
function Home() {

    const navigate = useNavigate();

    const handleHome = () => {
      navigate('/home/customer');
    };
    const CustomerUserID = localStorage.getItem('CustomerUserID');
    const {  id } = useParams();
    // alert(id);
    function Print() {
        // State to hold the form data
        const [formData, setFormData] = useState({
            fileName: '',
            numOfPages: '',
            size: '',
            numOfCopies: '',
            isHori: false,
            isDoubleSided: false,
            pageSize: 'A4',
        });
    
        // Handle input changes
        const handleChange = (e) => {
            const { name, value, type, checked } = e.target;
            setFormData({
                ...formData,
                [name]: type === 'checkbox' ? checked : value,
            });
        };
    
        // Handle the print operation
        const handlePrint = async () => {
            const printJob = [
                {
                    fileName: formData.fileName,
                    numOfPages: Number(formData.numOfPages),
                    size: parseFloat(formData.size),
                    numOfCopies: Number(formData.numOfCopies),
                    isHori: formData.isHori,
                    isDoubleSided: formData.isDoubleSided,
                    pageSize: formData.pageSize,
                }
            ];
            try {
                const response = await axios.post(`http://localhost:8081/student/${CustomerUserID}/print?printer-id=${id}`, printJob, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });
                alert(response.data.message);
                console.log('Print job successful:', response.data);
                // Handle success (e.g., show a success message)
            } catch (error) {
                console.error('Error printing document:', error);
    
                // Log the response if available
                if (error.response) {
                    console.error('Error response data:', error.response.data);
                    alert(`Error: ${error.response.data.message || 'Unknown error'}`);
                } else {
                    alert('An unexpected error occurred.');
                }
            }
        };
    
        return (
        <Box sx={{ bgcolor: '#FFFFFF', width: '60%', borderRadius: '15px', mx: 'auto', mt: 4, padding: 3,boxShadow: '0 4px 20px rgba(0, 0, 0, 0.1)' }}>
            <h1>Print Document</h1>
            <form onSubmit={(e) => { e.preventDefault(); handlePrint(); }}>
                <TextField
                    label="File Name"
                    variant="outlined"
                    name="fileName"
                    value={formData.fileName}
                    onChange={handleChange}
                    required
                    fullWidth
                    margin="normal"
                />
                <TextField
                    label="Number of Pages"
                    variant="outlined"
                    type="number"
                    name="numOfPages"
                    value={formData.numOfPages}
                    onChange={handleChange}
                    required
                    fullWidth
                    margin="normal"
                />
                <TextField
                    label="Size (MB)"
                    variant="outlined"
                    type="number"
                    name="size"
                    value={formData.size}
                    onChange={handleChange}
                    required
                    fullWidth
                    margin="normal"
                />
                <TextField
                    label="Number of Copies"
                    variant="outlined"
                    type="number"
                    name="numOfCopies"
                    value={formData.numOfCopies}
                    onChange={handleChange}
                    required
                    fullWidth
                    margin="normal"
                />
                <FormControlLabel
                    control={
                        <Checkbox
                            name="isHori"
                            checked={formData.isHori}
                            onChange={handleChange}
                        />
                    }
                    label="Horizontal Orientation"
                />
                <FormControlLabel
                    control={
                        <Checkbox
                            name="isDoubleSided"
                            checked={formData.isDoubleSided}
                            onChange={handleChange}
                        />
                    }
                    label="Double-Sided"
                />
                <FormControl fullWidth margin="normal">
                    {/* <InputLabel >Page Size</InputLabel> */}
                    <Select
                        name="pageSize"
                        value={formData.pageSize}
                        onChange={handleChange}
                    >
                        <MenuItem value="A4">A4</MenuItem>
                        <MenuItem value="A3">A3</MenuItem>
                        {/* <MenuItem value="Letter">Letter</MenuItem> */}
                        {/* Add more options as needed */}
                    </Select>
                </FormControl>
                <Button variant="contained" color="primary" type="submit" fullWidth>
                    Print
                </Button>
            </form>
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
        <Print/>
    </div>
  );
}

export default Home;


 