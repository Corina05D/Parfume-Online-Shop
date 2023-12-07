import React, {useEffect, useState} from 'react'
import Announcement from "../components/Announcement";
import Navbar from "../components/Navbar";
import styled from "styled-components";
import {Link} from "@mui/material";
import {useLocation, useNavigate} from "react-router-dom";

const Container= styled.div`
`;

const Wrapper= styled.div`
   padding:20px;
`;

const Title= styled.h1`
   font-weight:300;
   text-align:center;
`;

const Top= styled.div`
   display:flex;
   align-items:center;
   justify-content: space-between;
`;

const TopButton= styled.button`
   padding:10px;
   font-weight:600;
   cursor:pointer;
`;

const TopTexts= styled.div`
`;

const TopText= styled.span`
   text-decoration: underline;
   cursor:pointer;
   margin: 0px 10px;
`;

const Cart = () =>{
    const navigate = useNavigate();

    const [products, setProducts] = useState([]);

    const { state } = useLocation();
    const { productMap } = state !== null ? state : {productMap:{}};
    useEffect(() =>{
        for (var productId in productMap){
            fetch(`http://localhost:8080/produse/${productId}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Failed to fetch product');
                    }
                })
                .then(data => {
                    setProducts(prevProducts => prevProducts.find(product => product === data) ? prevProducts : [...prevProducts, data]);
                })
        }
    }, []);

    useEffect(() => {
        console.log(products);
    }, [products]);

    const handleContinue = () => {
        navigate("/");
    }

    const createOrder = (products) => {
        const productArray = Object.values(products);

        const orderRequest = {
            products: productArray,
            // Add any additional fields to the order request as needed
        };
        fetch('http://localhost:8080/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(orderRequest),
        })
            .then(response => {
                if (response.ok) {
                    return response.json();alert("Comanda plasata cu succes!");
                } else {
                    throw new Error('Failed to create order');
                }
            })
            .then(data => {
                // Handle the response data, if needed
                console.log('Order created:', data);
            })
            .catch(error => {
                console.error('Error creating order:', error);
            });
    };

    return(
        <Container>
            <Navbar/>
            <Announcement/>
            <Wrapper>
                <Title>Your Bag</Title>
                <Top>
                    <button onClick={handleContinue}>Continue Shopping</button>
                    <TopButton onClick={() => createOrder(products)}>Buy Now</TopButton>
                </Top>
                <div>
                    <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                        {products.map(product => (
                            <div key={product.id} style={{ width: '33,33%' }}>
                                <h2>{product.name}</h2>
                                <p>Price: {product.price * productMap[product.id]}</p>
                                <p>Producer: {product.producer}</p>
                                <p>Quantity: {productMap[product.id]}</p>
                                <img src={product.img} alt={product.name} />
                            </div>
                        ))}
                    </div>
                </div>
            </Wrapper>
        </Container>
    )
}

export default Cart