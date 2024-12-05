import React, { useState } from 'react';
import {
    AppBar,
    Toolbar,
    Button,
    Typography,
    Box,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TableSortLabel,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import NotificationsIcon from '@mui/icons-material/Notifications';
import SettingsIcon from '@mui/icons-material/Settings';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { styled } from '@mui/material/styles';

const generateRandomName = () => {
    const firstNames = ['Nguyễn Văn', 'Trần Thị', 'Lê Văn', 'Phạm Minh', 'Hoàng Anh', 'Đặng Quốc', 'Bùi Thị', 'Vũ Hoàng', 'Ngô Minh', 'Lý Bình'];
    const lastNames = ['Huy', 'Khang', 'Bảo', 'Minh', 'Phúc', 'Anh', 'Khoa', 'Phát', 'Đạt', 'Khôi'];
    const randomFirstName = firstNames[Math.floor(Math.random() * firstNames.length)];
    const randomLastName = lastNames[Math.floor(Math.random() * lastNames.length)];
    return `${randomFirstName} ${randomLastName}`;
};

const students = Array.from({ length: 100 }, (_, index) => ({
    id: index + 1,
    name: generateRandomName(),
    studentId: `SV${String(index + 1).padStart(3, '0')}`,
    paperLeft: Math.floor(Math.random() * 101), // Random paper left between 0 and 100
    image: '/ICON_NAM.png', // Placeholder image
}));

const Sidebar = styled('div')({
    width: '150px',
    backgroundColor: '#66B2FF',
    height: '100%',
    position: 'fixed',
    padding: '20px',
    marginTop: '20px',
});

const MainContent = styled('div')({
    marginLeft: '170px',
    padding: '20px',
    flexGrow: 1,
});

function StudentManagement() {
    const navigate = useNavigate();
    const [open, setOpen] = useState(false);
    const [selectedStudent, setSelectedStudent] = useState(null);

    const handleClickOpen = (student) => {
        setSelectedStudent(student);
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setSelectedStudent(null);
    };

    const handlePaperChange = (event) => {
        if (selectedStudent) {
            const updatedStudent = {
                ...selectedStudent,
                paperLeft: event.target.value,
            };
            setSelectedStudent(updatedStudent);
        }
    };

    return (
        <div>
            <Sidebar>
                <Button fullWidth onClick={() => navigate('/')} sx={{ display: 'flex', justifyContent: 'flex-start' }}>
                    
                    <img src="/Avatar.png" alt="Icon" width="60" height="30" />
                    <Typography variant='h5' style={{ fontWeight: 'bold' }} align='left' sx={{ flexGrow: 1 }} color='#FFFFFF'> SSPS </Typography>
                </Button>
                <Button fullWidth onClick={() => navigate('/home/admin')} sx={{ textAlign: 'left', justifyContent: 'flex-start' , color: '#ffffff'}}>Trang chủ</Button>
                <Button fullWidth onClick={() => navigate('/home/text')} sx={{ textAlign: 'left', justifyContent: 'flex-start' , color: '#ffffff'}}>Tin nhắn</Button>
                <Button fullWidth onClick={() => navigate('/home/admin/student')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Sinh viên</Button>
                <Button fullWidth onClick={() => navigate('/home/admin/printer')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Máy in</Button>
                <Button fullWidth onClick={() => navigate('/home/prints')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Quy định</Button>
                <Button fullWidth onClick={() => navigate('/home/prints')} sx={{ textAlign: 'left', justifyContent: 'flex-start', color: '#ffffff' }}>Thống kê</Button>
            </Sidebar>
            <MainContent>
                <AppBar position="static" sx={{ bgcolor: '#66B2FF' }}>
                    <Toolbar>
                        <Box sx={{ marginLeft: 'auto', display: 'flex', alignItems: 'center' }}>
                            <Button color="inherit"><NotificationsIcon /></Button>
                            <Button color="inherit"><SettingsIcon /></Button>
                            <Button color="inherit"><AccountCircleIcon /></Button>
                        </Box>
                    </Toolbar>
                </AppBar>
                <Typography variant="h4" sx={{ fontWeight: 'bold', marginBottom: 2, padding: 3 }}>
                    Quản lý sinh viên
                </Typography>
                <Box sx={{ marginTop: 2 }}>
                    <TableContainer>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Hình ảnh</TableCell>
                                    <TableCell>
                                        <TableSortLabel>Tên sinh viên</TableSortLabel>
                                    </TableCell>
                                    <TableCell>Mã số sinh viên</TableCell>
                                    <TableCell>Số lượng giấy còn lại</TableCell>
                                    
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {students.map((student) => (
                                    <TableRow key={student.id} hover onClick={() => handleClickOpen(student)}>
                                        <TableCell>
                                            <img src={student.image} alt="Student" style={{ width: '50px', height: 'auto' }} />
                                        </TableCell>
                                        <TableCell>{student.name}</TableCell>
                                        <TableCell>{student.studentId}</TableCell>
                                        <TableCell>{student.paperLeft}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>

                {/* Dialog for detailed student information */}
                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Thông tin chi tiết sinh viên</DialogTitle>
                    <DialogContent>
                        {selectedStudent && (
                            <div>
                                <Typography variant="h6">{selectedStudent.name}</Typography>
                                <Typography variant="body1">Mã số sinh viên: {selectedStudent.studentId}</Typography>
                                <Box sx={{display:'flex', alignItems:'center'}}>
                                <Typography variant="body1">Lượng giấy:</Typography>
                                <TextField
                                    type="number"
                                    value={selectedStudent.paperLeft}
                                    onChange={handlePaperChange}
                                    variant="outlined"
                                    sx={{ marginBottom: 2, width:'50%'}}
                                />
                                </Box>
                            </div>
                        )}
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">Đóng</Button>
                    </DialogActions>
                </Dialog>
            </MainContent>
        </div>
    );
}

export default StudentManagement;