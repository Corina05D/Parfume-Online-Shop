import React from  'react'
import styled from "styled-components";
import {Link, useNavigate} from "react-router-dom";
import {useRef } from "react";

const Container = styled.div`
  width:100vw;
  height:100vh;
  background:linear-gradient(rgba(255,255,255,0.5),rgba(255,255,255,0.5)),
  url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAGtL17v19ZHJqcxJHDOUJ2mmg_xm_jBH0zA&usqp=CAU") center;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Wrapper = styled.div`
  width: 25%;
  padding: 20px;
  background-color: white;
  
`;

const Title = styled.h1`
  font-size: 24px;
  font-weight: 300;
`;

const Input = styled.input`
  flex:1;
  min-width: 40%;
  margin:10px 0px;
  padding: 10px;
`;

const Button = styled.button`
  width: 40%;
  border: none;
  padding 15px 20px;
  background-color: teal;
  color: white;
  cursor: pointer;
  margin-bottom: 10px;
`;




const Login = () =>{
    const nameRef = useRef();
    const passwordRef = useRef();
    const navigate = useNavigate();

    function login(){
        const name = nameRef.current.value;
        const password = passwordRef.current.value;

        const body = {
            name: name,
            password: password
        }

        fetch("http://localhost:8080/login", {
            method: "POST",
            body: JSON.stringify(body),
            headers: {
                "Content-Type" : "application/json"
            }
        }).then((response) => {
            if(response.status === 400) {
                response.json().then((body) => {
                    console.log(body);
                    if(body.message === ""){
                        alert("Nume sau parola invalida");
                    }
                    else{
                        alert(body.message);
                    }
                })
            } else {
                response.json().then((body) => {
                    console.log(body);
                    navigate("/", {
                        state : {
                            userId: body.id
                        }
                    });
                })
            }
        });

    }



    return(
        <Container>
            <Wrapper>
                <Title>SIGN IN</Title>

                    <Input placeholder="name" ref={nameRef}/>
                    <Input placeholder="password" ref={passwordRef}/>
                    <Button onClick={login}>LOGIN</Button>
                    <Link to="/register"><button>CREATE A NEW ACCOUNT</button></Link>

            </Wrapper>
        </Container>
    )
}

export default Login
