@extends('user.layouts.app')

@section('content')
<div class="card-header">{{ __('Criar Utilizador') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif
<form action="{{ route('user.store') }}" method="POST">
        @csrf
        <div class="form-group">
            <label for="txtName">Nome Próprio:</label>
            <input type="text" class="form-control" id="txtName" placeholder="Nome Próprio" name="txtName">
        </div>
        <div class="form-group">
            <label for="txtDate">Data de Nascimento:</label>
            <input type="text" class="form-control" id="txtDate" placeholder="Data de Nascimento" name="txtDate">
        </div>
        <div class="form-group">
            <label for="txtEmail">Email:</label>
            <input type="text" class="form-control" id="txtEmail" placeholder="Email" name="txtEmail">
        </div>
        <div class="form-group">
            <label for="txtPhone">Télemovel:</label>
            <input type="text" class="form-control" id="txtPhone" placeholder="Télemovel" name="txtPhone">
        </div>
        <div class="form-group">
            <label for="txtUsername">Nome de utilizador:</label>
            <input type="text" class="form-control" id="txtUsername" placeholder="Nome de utilizador" name="txtUsername">
        </div>
        <div class="form-group">
            <label for="txtPassword">Palavra-chave:</label>
            <input type="text" class="form-control" id="txtPassword" placeholder="Palavra-chave" name="txtPassword" >
        </div>
        <div class="form-group">
            <label for="txtImage">Imagem:</label>
            <input type="text" class="form-control" id="txtImage" placeholder="Imagem" name="txtImage">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    {{ __('You are logged in!') }}
</div>
@endsection