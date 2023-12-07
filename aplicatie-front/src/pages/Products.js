import React, { useState, useEffect } from 'react';
import {useNavigate} from "react-router-dom";

function Products(params) {
    const navigate = useNavigate();

    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [cart,setCart]=useState({});

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = () => {
        fetch("http://localhost:8080/produse/all")
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to fetch products');
                }
            })
            .then(data => {
                setProducts(data);
                setLoading(false);
            })
            .catch(error => {
                setError(error.message);
                setLoading(false);
            });
    };

    const addToCart = productId => {
        const aux = cart;
        if(aux[productId] === undefined){
            aux[productId] = 1;
        }
        else{
            aux[productId]++;
        }
        setCart(aux);
        console.log(`Product ${productId} added to cart`);
        console.log(cart);
    };

    const addToFavorites = productId => {
        // Logic to add the product to favorites
        console.log(`Product ${productId} added to favorites`);
    };

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    const handleCart = () => {
        navigate("/cart", {state: {productMap: cart}})
    }

    return (
        <div>
            <h1>Products</h1>
            <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                {products.map(product => (
                    <div key={product.id} style={{ width: '33,33%' }}>
                        <h2>{product.name}</h2>
                        <p>Price: {product.price}</p>
                        <p>Producer: {product.producer}</p>
                        <p>Quantity: {product.quantity}</p>
                        <img src={product.img} alt={product.name} />
                        <button
                            onClick={() => addToCart(product.id)}
                            style={{
                                display: 'flow',
                                border: '2px solid black',
                                backgroundColor: 'blue',
                                color: 'white',
                                fontSize: '16px',
                            }}
                        >
                            Add to Cart
                        </button>
                        <button
                            onClick={() => addToFavorites(product.id)}
                            style={{
                                display: 'flow',
                                border: '2px solid black',
                                backgroundColor: 'yellow',
                                color: 'darkred',
                                fontSize: '16px',
                            }}
                        >
                            Add to Favorites
                        </button>
                    </div>
                ))}
            </div>
            <div>
                <button onClick={handleCart} style={{
                    display: 'flow',
                    border: '2px solid black',
                    backgroundColor: 'red',
                    color: 'white',
                    fontSize: '20px',
                }}>Go to cart</button>
            </div>
        </div>
    );
}

export default Products;