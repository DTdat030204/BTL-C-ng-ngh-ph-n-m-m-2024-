import { useState } from 'react';

import Container from '@mui/material/Container'
import Typography  from '@mui/material/Typography'
import TextField from '@mui/material/TextField'
import Button from '@mui/material/Button'
import Box from '@mui/material/Box'

import SendIcon from '@mui/icons-material/Send'

import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

import Rating from '@mui/material/Rating'


function Create()
{
    const [name,setName]=useState('')
    const [details, setDetails] = useState('')
    const [animal, setAnimal] = useState('')
    const [rating, setRating] = useState(0)

    const handleSubmit = (e) => {
        e.preventDefault() 

        if (name && details && animal)
        {
            console.log(name,details,animal,rating);
        }
        else
        {
            alert("Please fill out all fields")
            console.log("thieu data")
        }
    }

    return( 
        <Container>
            <Typography gutterBottom variant='h1'> </Typography>

            <Typography variant='h3' align='center' gutterBottom> 
                Create new students
            </Typography>
   


            <form noValidate autoComplete='off' onSubmit={handleSubmit}>

                <Box pb={3}>
                    <TextField label='Name' variant='standard' fullWidth required onChange={(e)=>setName(e.target.value)}/>
                    <TextField label='Detail' variant='standard' fullWidth multiline rows={3} onChange={(e)=>setDetails(e.target.value)} />
                    <RadioGroup row onChange={(e)=>setAnimal(e.target.value)} >
                        <FormControlLabel value='heo' control={<Radio />} label='heo' />
                        <FormControlLabel value='bo' control={<Radio />} label='bo' />
                        <FormControlLabel value='ga' control={<Radio />} label='ga ' />
                    </RadioGroup>
                    <Rating value={rating} onChange={(e)=> setRating(~~e.target.value)}/>
                </Box>
                  <Button type='submit' variant='contained' startIcon={<SendIcon/> }>Submit</Button>
            </form>

        </Container>
        )
}
export default Create 