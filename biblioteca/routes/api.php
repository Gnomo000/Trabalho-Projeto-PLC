<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AppuserController;
use App\Http\Controllers\BookController;
use App\Http\Controllers\RequisitionController;

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
Route::post('users', [AppuserController::class, 'addUser']);
Route::get('users/', [AppuserController::class, 'getAllUsers']);
Route::get('users/{email}', [AppuserController::class, 'getUserByEmail']);
Route::get('users/{email}/{password}', [AppuserController::class, 'getUserByPasswordAndEmail']);
Route::put('users/{id}', [AppuserController::class, 'updateUser']);
Route::delete('users/{id}', [AppuserController::class, 'deleteUser']);

//BOOK
Route::post('books', [BookController::class, 'addBook']);
Route::delete('books/{id}', [BookController::class, 'deleteBook']);
Route::get('books', [BookController::class, 'getAllBooks']);
Route::get('books/quantity', [BookController::class, 'getAllMoreZero']);
Route::get('books/{title}', [BookController::class, 'getBookByTitle']);
Route::get('booksId/{id}', [BookController::class, 'getBookById']);
Route::put('books/{id}', [BookController::class, 'updateBook']);
Route::get('bookstype/{string}', [BookController::class, 'getBookByTitleList']);
Route::get('bookstype/', [BookController::class, 'getBookByTitleListEmpty']);
Route::put('booksQuantity/{title}/{quantity}', [BookController::class, 'updateBookQuantity']);

//REQUISITION
Route::post('requisitions', [RequisitionController::class, 'addRequisition']);
Route::get('requisitions', [RequisitionController::class, 'getAllRequisitions']);
Route::get('requisitions/{email}', [RequisitionController::class, 'getRequisitionByEmail']);
Route::get('requisitionsId/{id}', [RequisitionController::class, 'getRequisitionById']);
Route::get('requisitions/{email}/{string}', [RequisitionController::class, 'getRequisitionByTitle']);
Route::put('requisitions/{id}/{status}', [RequisitionController::class, 'updateRequisition']);