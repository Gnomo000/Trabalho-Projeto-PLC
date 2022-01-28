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

        return response()->json(["message" => "requests record created"], 201);
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
            return response()->json(["message" => "Student not found"], 404);
          }
    }

    public function getUserByPasswordAndEmail($email,$password){
        if (User::where('email',$email)->where('password',$password)->exists()) {
            $userEmailAndPass = User::where('email',$email)->where('password',$password)->get()->toJson(JSON_PRETTY_PRINT);
            return response($userEmailAndPass,200);
        }else{
            return response()->json(["message" => "User not found"],404);
        }
    }


//BOOK
//BOOK
//BOOK

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
              "message" => "Book by title not found"
            ], 404);
        }
    }

    public function getBookById($id) {
        if (Book::where('id', $id)->exists()) {
            $id = Book::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
            return response($id, 200);
        }else {
            return response()->json([
              "message" => "Book by id not found"
            ], 404);
        }
    }
    
    public function updateBook(Request $request, $id) {
        if (Book::where('id', $id)->exists()) {
            $books = Book::find($id);
            $books->quantity = is_null($request->quantity) ? $books->quantity : $request->quantity;
            $books->save();
    
            return response()->json([
                "message" => "records updated successfully"
            ], 200);
            } else {
            return response()->json([
                "message" => "Book to update not found"
            ], 404);
            
        }
    }

    public function getBookByTitleList($column, $string){

        if (Book::where($column, 'LIKE', '%' . $string . '%')->exists()) {
            $books = Book::where($column, 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($books,200);
        }else{
            return response()->json([
                "message" => "Book by word not found"
            ],404);
        }
    }


//REQUISITION
//REQUISITION
//REQUISITION

    public function getAllRequisitions(){
        $requisitions = Requisition::get()->toJson(JSON_PRETTY_PRINT);
        return response($requisitions, 200);
    }

    public function getRequisitionByEmail($email){
        if (Requisition::where('email', $email)->exists()) {
            $requisition = Requisition::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($requisition, 200);
          } else {
            return response()->json(["message" => "Student not found"], 404);
          }
    }

    public function addRequisition(Request $request){
        $requisitions = new Requisition;
        $requisitions->email = $request->email;
        $requisitions->title = $request->title;
        $requisitions->requestDate = $request->requestDate;            
        $requisitions->deliverDate = $request->deliverDate;
        $requisitions->quantity = $request->quantity;
        $requisitions->status = $request->status;
        $requisitions->save();

        return response()->json(["message" => "requests record created"], 201);

    }
    
    public function getRequisitionById($id) {
        if (Requisition::where('id', $id)->exists()) {
            $id = Requisition::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
            return response($id, 200);
        }else {
            return response()->json([
              "message" => "Book by id not found"
            ], 404);
        }
    }

    public function getRequisitionByTitle($column, $string){

        if (Requisition::where($column, 'LIKE', '%' . $string . '%')->exists()) {
            $requisitions = Requisition::where($column, 'LIKE', '%' . $string . '%')->get()->toJson(JSON_PRETTY_PRINT);
            return response($requisitions,200);
        }else{
            return response()->json([
                "message" => "YESNTSS by word not found"
            ],404);
        }
    }
    
    public function updateRequisition(Request $request, $id) {
        if (Requisition::where('id', $id)->exists()) {
            $requisitions = Requisition::find($id);
            $requisitions->status = is_null($request->status) ? $requisitions->status : $request->status;
            $requisitions->save();
    
            return response()->json([
                "message" => "records updated successfully"
            ], 200);
            } else {
            return response()->json([
                "message" => "Book to update not found"
            ], 404);
            
        }
    }
}

