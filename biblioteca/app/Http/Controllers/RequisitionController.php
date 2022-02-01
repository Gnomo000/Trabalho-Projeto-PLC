<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Requisition;

class RequisitionController extends Controller
{
    
    public function index()
    {
        $requisition = Requisition::all();
        return view('requisition.list', compact('requisition','requisition'));
    }


    public function create()
    {
        return view('requisition.create');
    }
 
    public function store(Request $request)
    {
        $request->validate([
            'txtEmail'=>'required',
            'txtTitle'=>'required',
            'txtrequestDate'=>'required',
            'txtdeliverDate'=>'required',
            'txtQuantity'=>'required',
            'txtStatus'=>'required'
        ]);
 
        $requisition = new Requisition([
            'email' => $request->get('txtEmail'),
            'title'=> $request->get('txtTitle'),
            'requestDate'=> $request->get('txtrequestDate'),
            'deliverDate'=> $request->get('txtdeliverDate'),
            'quantity'=> $request->get('txtQuantity'),
            'status'=> $request->get('txtStatus')
        ]);
 
        $requisition->save();
        return redirect('/requisition')->with('success', 'Requisição has been added');
    }
 
    public function show(Requisition $requisition)
    {
        return view('requisition.view',compact('requisition'));
    }
 
    public function edit(Requisition $requisition)
    {
        return view('requisition.edit',compact('requisition'));
    }
 
    public function update(Request $request,$id)
    {
        $request->validate([
            'txtEmail'=>'required',
            'txtTitle'=> 'required',
            'txtrequestDate' => 'required',
            'txtdeliverDate' => 'required',
            'txtQuantity' => 'required',
            'txtStatus' => 'required'
        ]);
 
 
        $requisition = Requisition::find($id);
        $requisition->email = $request->get('txtEmail');
        $requisition->title = $request->get('txtTitle');
        $requisition->requestDate = $request->get('txtrequestDate');
        $requisition->deliverDate = $request->get('txtdeliverDate');
        $requisition->Quantity = $request->get('txtQuantity');
        $requisition->Status = $request->get('txtStatus');
 
        $requisition->update();

        return redirect('/requisition')->with('success', 'Requisition updated successfully');
    }
 
    public function destroy(Requisition $requisition)
    {
        $requisition->delete();
        return redirect('/requisition')->with('success', 'Requisition deleted successfully');
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
