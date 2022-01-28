<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use App\Models\Book;
use App\Models\Request;

class ApiController extends Controller
{
    public function getAllBooks() {
        $books = Book::get()->toJson(JSON_PRETTY_PRINT);
        return response($books, 200);
    }



//REQUESTS
//REQUESTS


    //Adicionar em forma descendente por id
    public function getRequestListByEmail($email) {
        if (Request::where('email', $email)->exists()) {
            $request = Request::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($request, 200);
        }else{
            return response()->json(["message" => "Request not found"], 404);
        }
    }

    public function getRequestById($email) {
        if (Request::where('email', $email)->exists()) {
            $request = Request::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($request, 200);
        }else{
            return response()->json(["message" => "Request not found"], 404);
        }
    }

    public function addResquest(Request $request){
        $request = new Request;
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

}
