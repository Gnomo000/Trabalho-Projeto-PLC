@extends('user.layouts.app')

@section('content')
<div class="card-header">{{ __('Editar Utilizador') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif

<form method="post" action="{{ route('user.update',$user->id) }}" >
        @method('PATCH')
        @csrf
        <div class="form-group">
            <label for="txtName">Nome Próprio:</label>
            <input type="text" class="form-control" id="txtName" placeholder="Nome Próprio" name="txtName" value="{{ $book->name }}">
        </div>
        <div class="form-group">
            <label for="txtDate">Data de Nascimento:</label>
            <input type="text" class="form-control" id="txtDate" placeholder="Data de Nascimento" name="txtDate" value="{{ $book->date }}">
        </div>
        <div class="form-group">
            <label for="txtEmail">Email:</label>
            <input type="text" class="form-control" id="txtEmail" placeholder="Email" name="txtEmail" value="{{ $book->email }}">
        </div>
        <div class="form-group">
            <label for="txtPhone">Télemovel:</label>
            <input type="text" class="form-control" id="txtPhone" placeholder="Télemovel" name="txtPhone" value="{{ $book->phone }}">
        </div>
        <div class="form-group">
            <label for="txtUsername">Nome de utilizador:</label>
            <input type="text" class="form-control" id="txtUsername" placeholder="Nome de utilizador" name="txtUsername" value="{{ $book->username }}">
        </div>
        <div class="form-group">
            <label for="txtPassword">Palavra-chave:</label>
            <input type="text" class="form-control" id="txtPassword" placeholder="Palavra-chave" name="txtPassword" value="{{ $book->password }}">
        </div>
        <div class="form-group">
            <label for="txtImage">Imagem:</label>
            <input type="text" class="form-control" id="txtImage" placeholder="Imagem" name="txtImage" value="{{ $book->image }}">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    {{ __('You are logged in!') }}
</div>
@endsection