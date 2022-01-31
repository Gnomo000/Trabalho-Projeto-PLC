<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use App\Models\Book;
use App\Models\Requisition;

class ApiController extends Controller
{
    public function addUser(Request $request){
        $users = new User;
        $users->name = $request->name;
        $users->date = $request->date;
        $users->email = $request->email;            
        $users->phone = $request->phone;
        $users->username = $request->username;
        $users->password = $request->password;
        $users->image = $request->image;
        $users->save();

        return response()->json(["message" => "Utilizador Criado"], 201);
    }

    public function updateUser(Request $request, $id) {
        if (User::where('id', $id)->exists()) {
            $users = User::find($id);
            $users->name = is_null($request->name) ? $users->name : $request->name;
            $users->date = is_null($request->date) ? $users->date : $request->date;
            $users->email = is_null($request->email) ? $users->email : $request->email;
            $users->phone = is_null($request->phone) ? $users->phone : $request->phone;
            $users->username = is_null($request->username) ? $users->username : $request->username;
            $users->password = is_null($request->password) ? $users->password : $request->password;
            $users->image = is_null($request->image) ? $users->image : $request->image;
            $users->save();
    
            return response()->json([
                "message" => "User atualizado"
            ], 200);
            } else {
            return response()->json([
                "message" => "Nao foi encontrado o user"
            ], 404);
            
        }
    }

    public function deleteUser ($id) {
        if(User::where('id', $id)->exists()) {
          $users = User::find($id);
          $users->delete();
  
          return response()->json([
            "message" => "User eliminado"
          ], 202);
        } else {
          return response()->json([
            "message" => "User nao encontrado para eliminar"
          ], 404);
        }
    }

    public function getAllUsers(){
        $usersAll = User::get()->toJson(JSON_PRETTY_PRINT);
        return response($usersAll, 200);
    }

    public function getUserByEmail($email){
        if (User::where('email', $email)->exists()) {
            $user = User::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($user, 200);
          } else {
            return response()->json(["message" => "Users nao por email"], 404);
          }
    }

    public function getUserByPasswordAndEmail($email,$password){
        if (User::where('email',$email)->where('password',$password)->exists()) {
            $userEmailAndPass = User::where('email',$email)->where('password',$password)->get()->toJson(JSON_PRETTY_PRINT);
            return response($userEmailAndPass,200);
        }else{
            return response()->json(["message" => "User nao encontrado por email e pass"],404);
        }
    }





//BOOK
//BOOK
//BOOK

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
            return response()->json([
              "message" => "Livro nao encontrado pelo titulo"
            ], 404);
        }
    }

    public function getBookById($id) {
        if (Book::where('id', $id)->exists()) {
            $id = Book::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
            return response($id, 200);
        }else {
            return response()->json([
              "message" => "Livro nao encontrado por id"
            ], 404);
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
            return response()->json([
                "message" => "Livro nao encontado por titulo"
            ],404);
        }
    }

//REQUISITION
//REQUISITION
//REQUISITION

    public function addRequisition(Request $request){
        $requisitions = new Requisition;
        $requisitions->email = $request->email;
        $requisitions->title = $request->title;
        $requisitions->requestDate = $request->requestDate;            
        $requisitions->deliverDate = $request->deliverDate;
        $requisitions->quantity = $request->quantity;
        $requisitions->status = $request->status;
        $requisitions->save();

        return response()->json(["message" => "Requisicao adicionada"], 201);

    }

    public function getAllRequisitions(){
        $requisitions = Requisition::get()->toJson(JSON_PRETTY_PRINT);
        return response($requisitions, 200);
    }



    public function getRequisitionByEmail($email){
        if (Requisition::where('email', $email)->exists()) {
            $requisition = Requisition::where('email', $email)->orderBy('id','desc')->get()->toJson(JSON_PRETTY_PRINT);
            return response($requisition, 200);
          } else {
            return response()->json(["message" => "Requisicao nao encontrada por email"], 404);
          }
    }

    public function getRequisitionById($id) {
        if (Requisition::where('id', $id)->exists()) {
            $id = Requisition::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
            return response($id, 200);
        }else {
            return response()->json([
              "message" => "Requisicao nao encontrada por id"
            ], 404);
        }
    }

    public function getRequisitionByTitle($email, $string){
        $requisitionsTitle = Requisition::find($string);

        if (Requisition::where('title', 'LIKE', '%' . $string . '%')->exists()) {
            $requisitionsTitle = Requisition::where('email', $email)->where('title', 'LIKE', '%' . $string . '%')->orderBy('id','desc')->get()->toJson(JSON_PRETTY_PRINT);
            return response($requisitionsTitle,200);

        }elseif (Requisition::where('status', 'LIKE', '%' . $string . '%')->exists()) {
            $requisitionsTitle = Requisition::where('email', $email)->where('status', 'LIKE', '%' . $string . '%')->orderBy('id','desc')->get()->toJson(JSON_PRETTY_PRINT);
            return response($requisitionsTitle,200);

        }elseif (Requisition::where('requestDate', 'LIKE', '%' . $string . '%')->exists()) {
            $requisitionsTitle = Requisition::where('email', $email)->where('requestDate', 'LIKE', '%' . $string . '%')->orderBy('id','desc')->get()->toJson(JSON_PRETTY_PRINT);
            return response($requisitionsTitle,200);

        }else{
        return response()->json([
            "message" => "Livro nao encontado por titulo"
        ],404);
        }
    }

    public function updateBookQuantityasas(Request $request, $title, $quantity) {
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

    public function updateRequisition(Request $request, $id) {
        if (Requisition::where('id', $id)->exists()) {
            $requisitions = Requisition::find($id);
            $requisitions->status = is_null($request->status) ? $requisitions->status : $request->status;
            $requisitions->save();
            
            return response()->json([
                "message" => "Requisicao atualizada"
            ], 200);
            } else {
            return response()->json([
                "message" => "Requisicao para atualizar nao encontrada"
            ], 404);
            
        }
    }
}      