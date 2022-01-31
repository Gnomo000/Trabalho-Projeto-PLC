<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Requisition;

class RequisitionController extends Controller
{
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
            return response([], 200);
          }
    }

    public function getRequisitionById($id) {
        if (Requisition::where('id', $id)->exists()) {
            $id = Requisition::where('id', $id)->get()->toJson(JSON_PRETTY_PRINT);
            return response($id, 200);
        }else {
            return response()->json([], 404);
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
        return response()->json([],404);
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
