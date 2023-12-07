import React from  'react'
import styled from "styled-components";
import {useNavigate} from "react-router-dom";
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
  width: 40%;
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
  margin: 20px 10px 0px 0px;
  padding: 10px;
`;

const Agreement = styled.span`
  font-size: 12px;
  margin: 20px 0px;
`;

const Button = styled.button`
  width: 40%;
  border: none;
  padding 15px 20px;
  background-color: teal;
  color: white;
  cursor: pointer;
`;

const Register = () =>{
    const nameRef = useRef();
    const emailRef= useRef();
    const passwordRef = useRef();
    const password2Ref=useRef();
    const navigate = useNavigate();

    function register(){
        const name = nameRef.current.value;
        const email=emailRef.current.value;
        const password = passwordRef.current.value;
        const password2 = password2Ref.current.value;

        const body = {
            name: name,
            email: email,
            password: password
        }

        fetch("http://localhost:8080/register", {
            method: "POST",
            body: JSON.stringify(body),
            headers: {
                "Content-Type" : "application/json"
            }
        }).then((response) => {
            if(response.status === 400) {
                alert("User already exists!");
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
                <Title>CREATE AN ACCOUNT</Title>
                    <Input placeholder="name" ref={nameRef}/>
                    <Input placeholder="email" ref={emailRef}/>
                    <Input placeholder="password" ref={passwordRef}/>
                    <Input placeholder="confirm password" ref={password2Ref}/>
                    <Agreement>
                        By creating an account, I consent to the processing of my personal data
                        in accordance with the <b>PRIVACY POLICY</b>
                    </Agreement>
                    <Button onClick={register}>CREATE</Button>
            </Wrapper>
        </Container>
    )
}

export default Register