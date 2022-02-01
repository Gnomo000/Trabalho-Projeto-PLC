@extends('book.layouts.app')

@section('content')
<div class="card-header">{{ __('Criar Livro') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif
<form action="{{ route('book.store') }}" method="POST">
        @csrf
        <div class="form-group">
            <label for="txtTitle">Titulo:</label>
            <input type="text" class="form-control" id="txtTitle" placeholder="Titulo" name="txtTitle">
        </div>
        <div class="form-group">
            <label for="txtTitleEn">Titulo em Inglês:</label>
            <input type="text" class="form-control" id="txtTitleEn" placeholder="Titulo em Ingês" name="txtTitleEn">
        </div>
        <div class="form-group">
            <label for="txtAuthor">Autor:</label>
            <input type="text" class="form-control" id="txtAuthor" placeholder="Autor" name="txtAuthor">
        </div>
        <div class="form-group">
            <label for="txtEdition">Edição:</label>
            <input type="text" class="form-control" id="txtEdition" placeholder="Edição" name="txtEdition">
        </div>
        <div class="form-group">
            <label for="txtPublisher">Editora:</label>
            <input type="text" class="form-control" id="txtPublisher" placeholder="Editora" name="txtPublisher" >
        </div>
        <div class="form-group">
            <label for="txtSynopse">Sinopse:</label>
            <input type="text" class="form-control" id="txtSynopse" placeholder="Sinopse" name="txtSynopse" >
        </div>
        <div class="form-group">
            <label for="txtGenders">Generos:</label>
            <input type="text" class="form-control" id="txtGenders" placeholder="Generos" name="txtGenders" ">
        </div>
        <div class="form-group">
            <label for="txtQuantity">Quantidade:</label>
            <input type="text" class="form-control" id="txtQuantity" placeholder="Quantidade" name="txtQuantity" >
        </div>
        <div class="form-group">
            <label for="txtImage">Imagem:</label>
            <input type="text" class="form-control" id="txtImage" placeholder="Image" name="txtImage" ">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    {{ __('You are logged in!') }}
</div>
@endsection