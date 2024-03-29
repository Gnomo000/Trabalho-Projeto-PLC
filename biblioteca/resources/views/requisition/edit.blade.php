@extends('requisition.layouts.app')

@section('content')
@if (Route::has('login'))
@auth
<div class="card-header">{{ __('Editar Requisição') }}</div>
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

    <form method="post" action="{{ route('requisition.update',$requisition->id) }}" >
        @method('PATCH')
        @csrf
        <div class="form-group">
            <label for="txtEmail">Titulo:</label>
            <input type="text" class="form-control" id="txtEmail" placeholder="Titulo" name="txtEmail"  value="{{ $requisition->email }}">
        </div>
        <div class="form-group">
            <label for="txtTitle">Titulo em Inglês:</label>
            <input type="text" class="form-control" id="txtTitle" placeholder="Titulo em Ingês" name="txtTitle"  value="{{ $requisition->title }}">
        </div>
        <div class="form-group">
            <label for="txtrequestDate">Autor:</label>
            <input type="text" class="form-control" id="txtrequestDate" placeholder="Autor" name="txtrequestDate"  value="{{ $requisition->requestDate }}">
        </div>
        <div class="form-group">
            <label for="txtdeliverDate">Edição:</label>
            <input type="text" class="form-control" id="txtdeliverDate" placeholder="Edição" name="txtdeliverDate"  value="{{ $requisition->deliverDate }}">
        </div>
        <div class="form-group">
            <label for="txtQuantity">Editora:</label>
            <input type="text" class="form-control" id="txtQuantity" placeholder="Editora" name="txtQuantity" value="{{ $requisition->quantity }}">
        </div>
        <div class="form-group">
            <label for="txtStatus">Sinopse:</label>
            <input type="text" class="form-control" id="txtStatus" placeholder="Sinopse" name="txtStatus" value="{{ $requisition->status }}">
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