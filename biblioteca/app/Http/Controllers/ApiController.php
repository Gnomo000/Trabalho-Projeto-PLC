<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use App\Models\Book;
use App\Models\Requisition;

class ApiController extends Controller
{
    public function getAllBooks() {
        $books = Book::get()->toJson(JSON_PRETTY_PRINT);
        return response($books, 200);
    }



//REQUESTS
//REQUESTS


    //Adicionar em forma descendente por id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public function getRequestListByEmail($email) {
        if (Requisition::where('email', $email)->exists()) {
            $request = Requisition::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($request, 200);
        }else{
            return response()->json(["message" => "Request not found"], 404);
        }
    }

    public function getRequestById($email) {
        if (Requisition::where('email', $email)->exists()) {
            $request = Requisition::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($request, 200);
        }else{
            return response()->json(["message" => "Request not found"], 404);
        }
    }

    public function addResquest(Requisition $request){
        $request = new Requisition;
            $request->email = $request->email;
            $request->title = $request->title;
            $request->requestDate = $request->requestDate;
            $request->deliverDate = $request->diliverDate;
            $request->quantity = $request->quantity;
            $request->status = $request->status;
            $request->save();

            return response()->json([
                "message" => "requests record created"
            ], 201);

    }

    //getRequestByTitle FAZER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public function updateRequest(Request $request, $id){
        if (Request::where('id', $id)->exists()) {
            $request = Request::find($id);
            $request->status = is_null($request->status) ? $request->status : $request->status;
            $request->save();

            return response()->json([
                "message" => "status updated successfully"
            ], 200);
            } else {
            return response()->json([
                "message" => "request not found"
            ], 404);

        }

    }


//USERS
//USERS

    public function addUser(User $user){
        $user = new User;
            $user->name = $user->name;
            $user->date = $user->date;
            $user->email = $user->email;            
            $user->phone = $user->phone;
            $user->username = $user->username;
            $user->password = $user->password;
            $user->image = $user->image;
            $user->save();

            return response()->json([
                "message" => "requests record created"
            ], 201);

    }


}
