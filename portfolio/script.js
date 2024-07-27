
document.addEventListener("DOMContentLoaded", () => {
    const burger = document.querySelector('.burger');
    const navLinks = document.querySelector('.nav-links');

    burger.addEventListener('click', () => {
        navLinks.classList.toggle('active');
    });
});

ScrollReveal({
    distance: '80px',
    duration: 2000,
    delay: 200,
});

ScrollReveal().reveal('.home-content,heading', { origin:'top' });
ScrollReveal().reveal('.home-img , .contact form , .experience-container', { origin:'bottom' });
ScrollReveal().reveal('.home-contact h1 , .about-img ', { origin:'left' });
ScrollReveal().reveal('.home-contact p , .about-content', { origin:'right' });


const typed =new Typed('.multiple-text',{
    strings:['Front End Developing ', 'BackEnd Developing', 'Fullstack Developing'],
    typeSpeed:70,
    backSpeed:70,
    backDelay:1000,
    loop: true,
});

function sendMail(){
    var params={
        name : document.getElementById("name").value,
        email: document.getElementById("email").value,
        message: document.getElementById("message").value,
        subject: document.getElementById("subject").value,
        phonenum: document.getElementById("phonenum").value,
    };

    const serviceID = "service_abk1bup";
    const templateID = "template_pdlk0wk";

emailjs.send(serviceID,templateID,params)
.then(
    res =>{
        document.getElementById("name").value = "";
        document.getElementById("email").value = "";
        document.getElementById("message").value = "";
        document.getElementById("subject").value = "";
        document.getElementById("phonenum").value = "";
        console.log(res);
        alert("Message Sent Succesfully");
    })
    .catch((err) => console.log(err));
    
}


    