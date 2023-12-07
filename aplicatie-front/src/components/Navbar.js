import React from 'react'
import styled from "styled-components";
import {Search, ShoppingCartOutlined} from "@mui/icons-material";
import {Badge, Button} from "@mui/material";
import { saveAs } from 'file-saver';
import axiosInstance from "axios";
import {Link, useNavigate} from "react-router-dom";

const Container  = styled.div`
  height:80px;
`;

const Wrapper=styled.div`
   padding:10px 20px;
   display: flex;
   align-items:center;
   justify-content: space-between;
`;

const Left=styled.div`
  flex:1;
  display: flex;
  align-items: center;
`;

const Language = styled.span`
  font-size: 14px;
  cursor: pointer;
`;

const SearchContainer=styled.div`
  border: 0.5px solid lightgray;
  display: flex;
  align-items: center;
  margin-left: 25px;
  padding: 5px;
`;

const Input= styled.input`
  border: none;
`;

const Center=styled.div`
  flex:1;
  text-align: center;
`;

const Logo=styled.h1`
  font-weight:bold;
`;

const Right=styled.div`
  flex:1;
  display: flex;
  align-items: center;
  justify-content: flex-end;
`;

const MenuItem =  styled.div`
  font-size: 14px;
  cursor: pointer;
  margin-left: 25px;
`;

const Navbar = () => {
    function exportDataTXT(){
        fetch("http://localhost:8080/produse/export?fileType=txt")
            .then(response => {
                if (response.ok) {
                    // Successful response
                    return response.blob(); // Retrieve response as Blob
                } else {
                    // Handle error response
                    throw new Error('Request failed with status ' + response.status);
                }
            })
            .then(blobData => {
                // Process the received data (in this case, a Blob)
                // For example, you can download the Blob as a file
                const downloadUrl = URL.createObjectURL(blobData);
                const link = document.createElement('a');
                link.href = downloadUrl;
                link.download = 'produseLimitate.txt';
                link.click();
            })
            .catch(error => {
                // Handle any errors
                console.error(error);
            });
    }

    function exportDataXML(){
        fetch("http://localhost:8080/produse/export?fileType=xml")
            .then(response => {
                if (response.ok) {
                    // Successful response
                    return response.blob(); // Retrieve response as Blob
                } else {
                    // Handle error response
                    throw new Error('Request failed with status ' + response.status);
                }
            })
            .then(blobData => {
                // Process the received data (in this case, a Blob)
                // For example, you can download the Blob as a file
                const downloadUrl = URL.createObjectURL(blobData);
                const link = document.createElement('a');
                link.href = downloadUrl;
                link.download = 'produseLimitate.xml';
                link.click();
            })
            .catch(error => {
                // Handle any errors
                console.error(error);
            });
    }

    return(
        <Container>
            <Wrapper>
                <Left>
                    <Language>EN</Language>
                    <SearchContainer>
                        <Input/>
                        <Search style={{color:"gray",fontSize:16}}/>
                    </SearchContainer>
                    <Button onClick={() => exportDataTXT()}>TXT</Button>
                    <Button onClick={() => exportDataXML()}>XML</Button>
                </Left>
                <Center>
                    <Logo>ARMONIA</Logo>
                </Center>
                <Right>
                    <MenuItem><Link to="/register"><button>REGISTER</button></Link></MenuItem>
                    <MenuItem><Link to="/login"><button>SIGN IN</button></Link></MenuItem>
                </Right>
            </Wrapper>
        </Container>
    )
}

export default Navbar