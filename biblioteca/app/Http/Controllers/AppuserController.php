<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Appuser;

class AppuserController extends Controller
{

    public function index()
    {
        //
        $users = Appuser::all();
        return view('user.list', compact('users','users'));
    }









    public function addUser(Request $request){
        $users = new Appuser;
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
        if (Appuser::where('id', $id)->exists()) {
            $users = Appuser::find($id);
            $users->name = is_null($request->name) ? $users->name : $request->name;
            $users->date = is_null($request->date) ? $users->date : $request->date;
            $users->email = is_null($request->email) ? $users->email : $request->email;
            $users->phone = is_null($request->phone) ? $users->phone : $request->phone;
            $users->username = is_null($request->username) ? $users->username : $request->username;
            $users->password = is_null($request->password) ? $users->password : $request->password;
            $users->image = is_null($request->image) ? $users->image : $request->image;
            $users->save();
    
            return response()->json([
                "message" => "Appuser atualizado"
            ], 200);
            } else {
            return response()->json([
                "message" => "Nao foi encontrado o user"
            ], 404);
            
        }
    }

    public function deleteUser ($id) {
        if(Appuser::where('id', $id)->exists()) {
          $users = Appuser::find($id);
          $users->delete();
  
          return response()->json([
            "message" => "Appuser eliminado"
          ], 202);
        } else {
          return response()->json([
            "message" => "Appuser nao encontrado para eliminar"
          ], 404);
        }
    }

    public function getAllUsers(){
        $usersAll = Appuser::get()->toJson(JSON_PRETTY_PRINT);
        return response($usersAll, 200);
    }

    public function getUserByEmail($email){
        if (Appuser::where('email', $email)->exists()) {
            $user = Appuser::where('email', $email)->get()->toJson(JSON_PRETTY_PRINT);
            return response($user, 200);
          } else {
            return response()->json(["message" => "Users nao por email"], 404);
          }
    }

    public function getUserByPasswordAndEmail($email,$password){
        if (Appuser::where('email',$email)->where('password',$password)->exists()) {
            $userEmailAndPass = Appuser::where('email',$email)->where('password',$password)->get()->toJson(JSON_PRETTY_PRINT);
            return response($userEmailAndPass,200);
        }else{
            return response()->json(["message" => "Appuser nao encontrado por email e pass"],404);
        }
    }
}
