const customSelects = document.querySelectorAll("select");
const deleteBtn = document.getElementById('delete')


$(document).ready(function() {

    console.log("js running")
    handleCheckbox();

});

function handleCheckbox() {
    $(".btn-group").click(function(){


        $(".checkbox").toggleClass("checkbox-active");


    });
}


$(function() {
    $(".test").flatpickr();

});

const dontSort = new Choices('.dont-sort',
    {
        itemSelectText: '',
        removeItemButton: true,
        searchEnabled: false,
        shouldSort: false
    });

const choices = new Choices('select',
    {
        position: "bottom",
        searchEnabled: false,
        itemSelectText: '',
        removeItemButton: true,
    });

for (let i = 0; i < customSelects.length; i++)
{
    customSelects[i].addEventListener('addItem', function(event)
    {
        if (event.detail.value)
        {
            let parent = this.parentNode.parentNode
            parent.classList.add('valid')
            parent.classList.remove('invalid')
        }
        else
        {
            let parent = this.parentNode.parentNode
            parent.classList.add('invalid')
            parent.classList.remove('valid')
        }
    }, false);
}

deleteBtn.addEventListener("click", function(e)
{
    e.preventDefault()
    const deleteAll = document.querySelectorAll('.choices__button')
    for (let i = 0; i < deleteAll.length; i++)
    {
        deleteAll[i].click();
    }
});
