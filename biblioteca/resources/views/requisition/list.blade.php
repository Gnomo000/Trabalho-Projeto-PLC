@extends('book.layouts.app')

@section('content')
<div class="card-header">{{ __('Dashboard') }}</div>
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
    <div class="col-lg-12">
            <a class="btn btn-success" href="{{ route('requisition.create') }}">Add</a>
    </div>
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Titulo</th>
            <th>Data de requisição</th>
            <th>Data de entrega</th>
            <th>Quantidade</th>
            <th>Status</th>
            <th width="280px">Action</th>
        </tr>
        @php
            $i = 0;
        @endphp
        @foreach ($requisition as $requisition)
            <tr>
                <td>{{ ++$i }}</td>
                <td>{{ $requisition->email }}</td>
                <td>{{ $requisition->title }}</td>
                <td>{{ $requisition->requestDate }}</td>
                <td>{{ $requisition->deliverDate }}</td>
                <td>{{ $requisition->quantity }}</td>
                <td>{{ $requisition->status }}</td>
                <td>
                    <form action="{{ route('requisition.destroy',$requisition->id) }}" method="POST">
                        <a class="btn btn-info" href="{{ route('requisition.show',$requisition->id) }}">Show</a>
                        <a class="btn btn-primary" href="{{ route('requisition.edit',$requisition->id) }}">Edit</a>
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        @endforeach
    </table>

    

    {{ __('You are logged in!') }}
</div>
@endsection
