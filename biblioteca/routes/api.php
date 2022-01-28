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
Route::get('books', [ApiController::class, 'getAllBooks']);

Route::get('requests/{email}', [ApiController::class, 'getRequestListByEmail']);
Route::get('requests/{id}', [ApiController::class, 'getRequestById']);
Route::post('requests', [ApiController::class, 'addRequest']);
Route::get('requests/{id}', [ApiController::class, 'getRequestByTitle']);
