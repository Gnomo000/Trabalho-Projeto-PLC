<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Book;

class BookController extends Controller
{

    public function index()
    {
        //
        $books = Book::all();
        return view('book.list', compact('books','books'));
    }






















    public function addBook(Request $request){
        $books = new Book;
        $books->title = $request->title;
        $books->titleEn = $request->titleEn;
        $books->author = $request->author;            
        $books->edition = $request->edition;
        $books->publisher = $request->publisher;
        $books->synopse = $request->synopse;
        $books->genders = $request->genders;
        $books->quantity = $request->quantity;
        $books->image = $request->image;
        $books->save();

        return response()->json(["message" => "Livro Criado"], 201);
    }



    public function deleteBook ($id) {
        if(Book::where('id', $id)->exists()) {
        $books = Book::find($id);
        $books->delete();

        return response()->json([
            "message" => "Livro eliminado"
        ], 202);
        } else {
        return response()->json([
            "message" => "Livro nao encontrado para eliminar"
        ], 404);
        }
    }


    public function getAllBooks() {
        $books = Book::get()->toJson(JSON_PRETTY_PRINT);
        return response($books, 200);
    }

    public function getAllMoreZero(){
        $books = Book::where('quantity', '>', 0)->get()->toJson(JSON_PRETTY_PRINT);
        return response($books, 200);
    }

    public function getBookByTitle($title) {
        if (Book::where('title', $title)->exists()) {
            $books = Book::where('title', $title)->get()->toJson(JSON_PRETTY_PRINT);
            return response($books, 200);
        }else {
            return response()->json([], 404);
        }
    }

    public function getBookById($id) {
        if (Book::where('id', $id)->exists()) {
            $id = Book::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
            return response($id, 200);
        }else {
            return response()->json([], 404);
        }
    }
    
    public function updateBook(Request $request, $id) {
        if (Book::where('id', $id)->exists()) {
            $books = Book::find($id);
            $books->title = is_null($request->title) ? $books->title : $request->title;
            $books->titleEn = is_null($request->titleEn) ? $books->titleEn : $request->titleEn;
            $books->author = is_null($request->author) ? $books->author : $request->author;
            $books->edition = is_null($request->edition) ? $books->edition : $request->edition;
            $books->publisher = is_null($request->publisher) ? $books->publisher : $request->publisher;
            $books->synopse = is_null($request->synopse) ? $books->synopse : $request->synopse;
            $books->genders = is_null($request->genders) ? $books->genders : $request->genders;
            $books->quantity = is_null($request->quantity) ? $books->quantity : $request->quantity;
            $books->image = is_null($request->image) ? $books->image : $request->image;
            $books->save();
    
            return response()->json([
                "message" => "Livro atualizado"
            ], 200);
        } else {
            return response()->json([
                "message" => "Livro nao encontrado"
            ], 404);
            
        }
    }

    public function updateBookQuantity(Request $request, $title, $quantity) {
        if (Book::where('title', $title)->exists()) {
            $books = Book::find($title);
            $books = Book::where('title', $title)->decrement('quantity', $quantity);
    
            return response()->json([
                "message" => "Requisicao atualizada"
            ], 200);

        } else {
            return response()->json([
                "message" => "Requisicao para atualizar nao encontrada"
            ], 404);
        }
    }

    public function getBookByTitleList($string){

        if (Book::where('title', 'LIKE', '%' . $string . '%')->exists()) {
            $books = Book::where('title', 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($books,200);

        }elseif (Book::where('titleEn', 'LIKE', '%' . $string . '%')->exists()) {
            $books = Book::where('titleEn', 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($books,200);

        }elseif (Book::where('author', 'LIKE', '%' . $string . '%')->exists()) {
            $books = Book::where('author', 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($books,200);

        }elseif (Book::where('publisher', 'LIKE', '%' . $string . '%')->exists()) {
            $books = Book::where('publisher', 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($books,200);

        }elseif (Book::where('genders', 'LIKE', '%' . $string . '%')->exists()) {
            $books = Book::where('genders', 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($books,200);
    
        }else{
            return response()->json([],404);
        }
        
    }

    public function getBookByTitleListEmpty() {
        $books = Book::get()->toJson(JSON_PRETTY_PRINT);
        return response($books, 200);
    }

}
