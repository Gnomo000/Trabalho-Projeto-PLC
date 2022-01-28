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

            return response()->json([
                "message" => "requests record created"
            ], 201);

    public function getUserByEmail(){}

            if (User::where('email', $email)->exists()) {
                $users = User::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
                return response($users, 200);
        }else{
            return response()->json(["message" => "Student not found"], 404);
        }
    }
}
