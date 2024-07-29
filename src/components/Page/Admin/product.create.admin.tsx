'use client';

import { Modal } from '@mui/material';
import { useSession } from 'next-auth/react';
import type { UploadProps, UploadFile } from 'antd';
import { useState } from 'react';
import { Avatar, Flex, Input, Button, message, Upload } from 'antd';
import { handleUploadFile } from '@/utils/actions/actions';
import { SnippetsOutlined } from '@ant-design/icons';

interface IProps {
    isOpen: boolean;
    setOpen: (value: boolean) => void;
}

function CreateProduct({ isOpen, setOpen }: IProps) {
    const { data: sessionAuth } = useSession();

    const [file, setFile] = useState<string>('');
    const [fieldList, setFieldList] = useState<UploadFile[]>([]);

    const handleOnChangeFile = async (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            let fileFormData = new FormData();
            fileFormData.append('file', e.target.files[0]);

            fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/files?folder=products`, {
                method: 'POST',
                headers: {
                    Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJwZXJtaXNzaW9uIjpbIlJPTEVfVVNFUl9DUkVBVEUiLCJST0xFX1VTRVJfVVBEQVRFIl0sImV4cCI6MTcyOTgyNjExMiwiaWF0IjoxNzIxMTg2MTEyLCJ1c2VyIjp7ImlkIjoxLCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsImZ1bGxOYW1lIjoibGUgbmFtIn19.109HF0EMl51D0wdLgiJrhQd3eA7xMDRkmmcn3kGxmNUmA4pEhpvxNsnIHBRx_iOqcFHMLpyW9KswA03blBln5Q`,
                },
                body: fileFormData,
            }).then((res) => console.log('res', res));
        }
    };

    const handleOnClickSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        // e.preventDefault();
        // const typeOfFile = getTypeOfFile(file);
        // const createPost = await handleCreatePost({
        //     desc,
        //     target_type: typeOfFile.target_type,
        //     [typeOfFile.type]: file,
        // });
        // if (createPost.data) {
        //     revalidateGetPostsFollowing();
        //     setFieldList([]);
        //     setFile('');
        //     setDesc('');
        //     message.success(createPost.message);
        // } else {
        //     message.error(createPost.message);
        // }
    };

    return (
        <Modal
            open={isOpen}
            onClose={() => setOpen(false)}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <main>
                <div className="container px-4 bg-white">
                    <h1 className="mt-4"></h1>

                    {/* <!-- Content --> */}
                    <div className="row">
                        <div className="col-md-6 col-12 mx-auto">
                            <h1 className="mt-4">Create Product</h1>
                            <form>
                                {/* <!-- Email --> */}
                                <div className="mb-3 row">
                                    <div className="col">
                                        <label
                                            htmlFor="exampleInputEmail1"
                                            className="form-label"
                                        >
                                            Name:
                                        </label>

                                        <input
                                            type="number"
                                            className="form-control ${not empty priceError ? 'is-invalid' : ''}"
                                            id="exampleInputPassword1"
                                        />
                                    </div>
                                    {/* <!-- Password --> */}
                                    <div className="col">
                                        <label
                                            htmlFor="exampleInputPassword1"
                                            className="form-label"
                                        >
                                            Price:
                                        </label>

                                        <input
                                            type="number"
                                            className="form-control ${not empty priceError ? 'is-invalid' : ''}"
                                            id="exampleInputPassword1"
                                        />
                                    </div>
                                </div>
                                {/* <!-- Phone --> */}
                                <div className="mb-3">
                                    <label
                                        htmlFor="exampleInputTel"
                                        className="form-label"
                                    >
                                        Detail description:
                                    </label>

                                    <textarea
                                        className="form-control"
                                        id="exampleInputTel"
                                    >
                                        At w3schools.com you will learn how to make a
                                        website. They offer free tutorials in all web
                                        development technologies.
                                    </textarea>
                                </div>

                                {/* <!-- Full name --> */}
                                <div className="row mb-3">
                                    <div className="col">
                                        <label
                                            htmlFor="exampleInputFullName"
                                            className="form-label"
                                        >
                                            Short description:
                                        </label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="exampleInputFullName"
                                        />
                                    </div>
                                    <div className="col">
                                        <label
                                            htmlFor="exampleInputAddress"
                                            className="form-label"
                                        >
                                            {' '}
                                            Quantity{' '}
                                        </label>

                                        <input
                                            className="form-control ${not empty quantityError ? 'is-invalid' : ''}"
                                            id="exampleInputAddress"
                                        />
                                    </div>
                                </div>

                                <div className="row mb-3">
                                    <div className="col">
                                        <label
                                            htmlFor="exampleInputFullName"
                                            className="form-label"
                                        >
                                            Factory:
                                        </label>
                                        <select
                                            className="form-select"
                                            aria-label="Default select example"
                                        >
                                            <option value="Apple">Apple</option>
                                            <option value="Asus">Asus</option>
                                            <option value="Lenovo">Lenovo</option>
                                            <option value="Dell">Dell</option>
                                            <option value="Gigabyte">Gigabyte</option>
                                        </select>
                                    </div>
                                    <div className="col">
                                        <label
                                            htmlFor="exampleInputFullName"
                                            className="form-label"
                                        >
                                            Target:
                                        </label>
                                        <select
                                            className="form-select"
                                            aria-label="Default select example"
                                        >
                                            <option value="Student - office">
                                                Student - office
                                            </option>
                                            <option value="Gaming">Gaming</option>
                                        </select>
                                    </div>
                                </div>

                                <div className="mb-3 row">
                                    {/* <!-- Upload --> */}
                                    <div className="col-5">
                                        <label
                                            htmlFor="avatarFile"
                                            className="form-label"
                                        >
                                            Image:
                                        </label>
                                        <div className="input-group mb-3">
                                            <input
                                                type="file"
                                                className="form-control"
                                                id="avatarFile"
                                                name="lenamFile"
                                                accept=".png, .jpg, .jpeg"
                                                onChange={(e) => handleOnChangeFile(e)}
                                            />
                                        </div>
                                    </div>
                                    <div className="col-2">
                                        <img
                                            width="100%"
                                            style={{ height: '69px', display: 'none' }}
                                            id="avatarPreview"
                                        />
                                    </div>
                                </div>

                                <button type="submit" className="btn btn-primary">
                                    Create
                                </button>
                                <button
                                    type="submit"
                                    onClick={() => setOpen(false)}
                                    className="btn btn-danger"
                                >
                                    Close
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </Modal>
    );
}

export default CreateProduct;
