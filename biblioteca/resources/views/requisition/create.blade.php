@extends('requisition.layouts.app')

@section('content')
@if (Route::has('login'))
@auth
<div class="card-header">{{ __('Criar Requisição') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
<div class="col-lg-1">
    <a class="btn btn-primary" href="{{ url('requisition') }}"> Back</a>
</div>
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif

    <form action="{{ route('requisition.store') }}" method="POST">
        @csrf
        <div class="form-group">
            <label for="txtEmail">Email:</label>
            <input type="text" class="form-control" id="txtEmail" placeholder="Enter First Name" name="txtEmail">
        </div>
        <div class="form-group">
            <label for="txtTitle">Titulo:</label>
            <input type="text" class="form-control" id="txtTitle" placeholder="Enter Last Name" name="txtTitle">
        </div>
        <div class="form-group">
            <label for="txtrequestDate">Data Requisição:</label>
            <input type="text" class="form-control" id="txtrequestDate" placeholder="Enter Last Name" name="txtrequestDate">
        </div>
        <div class="form-group">
            <label for="txtdeliverDate">Data de Entrega:</label>
            <input type="text" class="form-control" id="txtdeliverDate" placeholder="Enter Last Name" name="txtdeliverDate">
        </div>
        <div class="form-group">
            <label for="txtQuantity">Quantidade:</label>
            <input type="text" class="form-control" id="txtQuantity" placeholder="Enter Last Name" name="txtQuantity">
        </div>
        <div class="form-group">
            <label for="txtStatus">Status:</label>
            <input type="text" class="form-control" id="txtStatus" placeholder="Enter Last Name" name="txtStatus">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

    
    {{ __('You are logged in!') }}
</div>

<div class="hidden fixed top-0 right-0 px-6 py-4 sm:block">
        <a href="{{ url('/home') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Home</a>
    @else
        <a href="{{ route('login') }}" class="text-sm text-gray-700 dark:text-gray-500 underline">Log in</a>

        @if (Route::has('register'))
            <a href="{{ route('register') }}" class="ml-4 text-sm text-gray-700 dark:text-gray-500 underline">Register</a>
        @endif
    @endauth
</div>
    @endif
@endsection