<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ApiController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
Route::post('users', [ApiController::class, 'addUser']);
Route::get('users/', [ApiController::class, 'getAllUsers']);
Route::get('users/{email}', [ApiController::class, 'getUserByEmail']);
Route::get('users/{email}/{password}', [ApiController::class, 'getUserByPasswordAndEmail']);

//BOOK
//BOOK
//BOOK

Route::get('books', [ApiController::class, 'getAllBooks']);
Route::get('books/quantity', [ApiController::class, 'getAllMoreZero']);
Route::get('books/{title}', [ApiController::class, 'getBookByTitle']);
Route::get('booksId/{id}', [ApiController::class, 'getBookById']);
Route::put('books/{id}', [ApiController::class, 'updateBook']); //feito pelo raw
Route::get('books/{column}/{string}', [ApiController::class, 'getBookByTitleList']);

//REQUISITION
//REQUISITION
//REQUISITION

Route::get('requisitions', [ApiController::class, 'getAllRequisitions']);
Route::get('requisitions/{email}', [ApiController::class, 'getRequisitionByEmail']);
Route::post('requisitions', [ApiController::class, 'addRequisition']);
Route::get('requisitionsId/{id}', [ApiController::class, 'getRequisitionById']);
Route::get('requisitions/{column}/{string}', [ApiController::class, 'getRequisitionByTitle']);
Route::put('requisitions/{id}', [ApiController::class, 'updateRequisition']); //feito pelo raw
