import React from 'react';
import Header from '../../components/headers';
import BookDetail from '../../components/bookdetail';

const BookDetailPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <div>
               <BookDetail/> 
            </div>
        </div>
    );
};


export default BookDetailPage;