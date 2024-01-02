const API_BASE_URL = 'http://localhost:8080';

function addBookFields() {
  const booksContainer = document.getElementById('booksContainer');

  const bookFields = document.createElement('div');
  bookFields.classList.add('bookFields');

  bookFields.innerHTML = `
    <label>Book Name:</label>
    <input class="bookName" type="text" required><br><br>

    <label>Genre:</label>
    <input class="genre" type="text" required><br><br>

    <label>Characters:</label>
    <input class="characters" type="text" required><br><br>

    <label>Chapter Names:</label>
    <input class="chapterNames" type="text" required><br><br>

    <label>New Ideas:</label>
    <input class="newIdeas" type="text"><br><br>

  booksContainer.appendChild(bookFields);
}

async function createAuthorWithMultipleBooks() {
  const authorName = document.getElementById('authorName').value;
  const age = parseInt(document.getElementById('age').value);
  const email = document.getElementById('email').value;

  const books = [];

  const bookFields = document.querySelectorAll('.bookFields');
  bookFields.forEach(bookField => {
    const bookName = bookField.querySelector('.bookName').value;
    const genre = bookField.querySelector('.genre').value;
    const characters = bookField.querySelector('.characters').value;
    const chapterNames = bookField.querySelector('.chapterNames').value;
    const newIdeas = bookField.querySelector('.newIdeas').value;

    const book = {
      bookName,
      genre,
      characters,
      chapterNames,
      newIdeas
    };

    books.push(book);
  });

  const authorData = {
    authorName,
    age,
    email,
    books,
  };

  try {
    const response = await fetch(`${API_BASE_URL}/authors/create-with-books`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(authorData),
    });

    if (response.ok) {
      alert('Author created successfully with books!');
    } else {
      alert('Failed to create author with books.');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('Error occurred while creating author with books.');
  }
}

document.getElementById('createAuthorWithBooksBtn').addEventListener('click', createAuthorWithMultipleBooks);



