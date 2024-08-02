
const dropdown = document.getElementById('myDropdown');
const aboutContent = document.getElementById('about');
const Alumini = document.getElementById('Alumini');
const Menu_content =document.getElementById('Menu');
const ContactUs_page =document.getElementById('contact');

dropdown.addEventListener('change', function () {
    const selectedOption = dropdown.value;

    // Hide all content divs
    aboutContent.style.display = 'none';
    Alumini.style.display = 'none';
    ContactUs_page.style.display='none';

    // Menu_content.style.display='block';

    // Show the selected content div
    if (selectedOption === 'about') {
        aboutContent.style.display = 'block';
        Menu_content.style.display='none';
        
    } else if (selectedOption === 'Alumini') {

        Alumini.style.display = 'block';
        Menu_content.style.display='none';
    } else if(selectedOption === 'contact'){

        ContactUs_page.style.display='block';
        Menu_content.style.display='none';
    }else if(selectedOption ==='Menu'){
        Menu_content.style.display='block';
    }
    // Add more conditions for additional options
});