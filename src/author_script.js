const API_BASE_URL = 'http://localhost:8080';

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('createForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const authorId = document.getElementById('authorId').value;
        const authorName = document.getElementById('authorName').value;
        const age = document.getElementById('age').value;
        const email = document.getElementById('email').value;

        const bookId = document.getElementById('bookId').value;
        const bookName = document.getElementById('bookName').value;
        const genre = document.getElementById('genre').value;
        const characters = document.getElementById('characters').value.split('\n');
        const chapterNames = document.getElementById('chapterNames').value.split('\n');
        const newIdeas = document.getElementById('newIdeas').value.split('\n');

        const authorData = {
            authorId: authorId,
            authorName: authorName,
            age: age,
            email: email,
            books: [{
                bookId: bookId,
                bookName: bookName,
                genre: genre,
                characters: characters,
                chapterNames: chapterNames,
                newIdeas: newIdeas
            }]
        };

        fetch('/authors/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(authorData)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Failed to create author.');
        })
        .then(data => {
            console.log('Author created:', data);
        })
        .catch(error => {
            console.error('Error creating author:', error.message);
        });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('updateForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const authorIdToUpdate = document.getElementById('authorIdToUpdate').value;
        const updatedAuthorName = document.getElementById('updatedAuthorName').value;
        const updatedAge = document.getElementById('updatedAge').value;
        const updatedEmail = document.getElementById('updatedEmail').value;

        const bookId = document.getElementById('bookId').value;
        const bookName = document.getElementById('bookName').value;
        const genre = document.getElementById('genre').value;
        const characters = document.getElementById('characters').value.split('\n');
        const chapterNames = document.getElementById('chapterNames').value.split('\n');
        const newIdeas = document.getElementById('newIdeas').value.split('\n');

        const updatedAuthorData = {
            authorName: updatedAuthorName,
            age: updatedAge,
            email: updatedEmail,
            books: [{
                bookId: bookId,
                bookName: bookName,
                genre: genre,
                characters: characters,
                chapterNames: chapterNames,
                newIdeas: newIdeas
            }]
        };

        fetch(`/authors/${authorIdToUpdate}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedAuthorData)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Failed to update author.');
        })
        .then(data => {
            console.log('Author updated:', data);
        })
        .catch(error => {
            console.error('Error updating author:', error.message);
        });
    });
});


document.addEventListener('DOMContentLoaded', function() {
    const deleteForm = document.getElementById('deleteForm');

    deleteForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const authorIdToDelete = document.getElementById('authorIdToDelete').value;

        fetch(`/authors/${authorIdToDelete}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                console.log('Author deleted successfully.');
            } else {
                throw new Error('Failed to delete author.');
            }
        })
        .catch(error => {
            console.error('Error deleting author:', error.message);
        });
    });
});





