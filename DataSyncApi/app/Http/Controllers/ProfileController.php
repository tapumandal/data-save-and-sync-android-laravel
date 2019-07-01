<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Profiles;
use DB;

class ProfileController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return json_encode(Profiles::all());
        // return 'index';
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        
        // $tableName = $request->all()["table"];
        // if($tableName == null || $tableName == "")
            $tableName = "profiles";
        $data = (array) json_decode($request->all()["profile"]);
        DB::table($tableName)->insert($data);

        $status["status"] = "success";
        return json_encode($status);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return json_encode(Profiles::find($id));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $profile = json_decode($request->all()["profile"]);
        $pro = (array) $profile->profile->data;
        $profile = Profiles::findOrFail($id);
        $profile->update($pro);

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $profile = Profiles::findOrFail($id);
        $profile->delete();

        return "Row Deleted successfully";
    }
}
